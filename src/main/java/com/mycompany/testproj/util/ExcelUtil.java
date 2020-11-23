package com.mycompany.testproj.util;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 엑셀 다운로드 유틸클래스
 * 
 * @author YongJin
 * 
 */
public class ExcelUtil {

	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	/** 
	 * 
	 * @param request 
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param beans
	 *            excel에 담길 데이타
	 * @param templateFileName
	 *            템플릿 파일명
	 * @param downloadFileName
	 *            다운로드할 엑셀 파일명
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void downloadExcel(HttpServletRequest request,HttpServletResponse response, Map beans, String templateFileName,String downloadFileName) throws Exception {

		String srcFile = request.getRealPath("") + "/WEB-INF/excelTemplate/"+ templateFileName;

		InputStream is = new FileInputStream(srcFile);

		XLSTransformer transformer = new XLSTransformer();
		Workbook workbook = transformer.transformXLS(is, beans);

		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		response.setHeader("Content-Disposition", "attachment;filename=\""+ java.net.URLEncoder.encode(downloadFileName, "UTF-8") + "\";");

		workbook.write(response.getOutputStream());
	}

	/**
	 * 엑셀데이터(파일)를 리스트 데이터로 반환
	 * 
	 * @param fileIn
	 *            InputStream
	 * @param startRow
	 *            데이터 시작 로우 0 base
	 * @param startCell
	 *            데이터 시작 컬럼 0 base
	 * @param colNames
	 *            컬럼 변수 명 배열 {"a","b","c"}
	 * @return
	 * @throws Exception
	 */
	public static List<PubMap> loadExcelToList(InputStream fileIn,int startRow, int startCell, String[] colNames) throws Exception {
		List<PubMap> list = new ArrayList<PubMap>();
		Workbook wb = null;

		if (fileIn == null || startRow < 0 || startCell < 0 || colNames == null
				|| colNames.length == 0) {
			return list;
		}

		try {
			wb = WorkbookFactory.create(fileIn); // new Workbook(fs);
		} catch (IOException e) {
			logger.error(Utils.getMethodName(), e);
			throw new RuntimeException("엑셀파일 로딩 실패");
		} catch (InvalidFormatException e) {
			logger.error(Utils.getMethodName(), e);
			throw new RuntimeException("엑셀파일 로딩 실패");
		} finally {// / IOException, InvalidFormatException
			if (fileIn != null) {
				fileIn.close();
			}
		}
		if (wb != null && wb.getSheetAt(0) != null) {
			Sheet sheet = wb.getSheetAt(0);

			int rowCnt = sheet.getPhysicalNumberOfRows();
			int colCnt = colNames.length;

			if (startRow > rowCnt) {
				logger.error(Utils.getMethodName(),	"엑셀파일 로딩 실패  startRow > rowCnt");
			}

			for (int rowIdx = startRow; rowIdx < rowCnt; rowIdx++) {
				// 행을 읽다
				Row xRow = sheet.getRow(rowIdx);
				if (xRow != null) {
					// 셀의 수
				int cellCnt = xRow.getPhysicalNumberOfCells();
				int notEmptyCnt = 0;
				PubMap rowData = new PubMap();
				for (int colIdx = 0, cellIdx = startCell; colIdx < colCnt; colIdx++, cellIdx++) {

					Object value = "";
					if (cellCnt > (cellIdx)) {
						// 셀값 읽기
						Cell xCell = xRow.getCell(cellIdx);
					if (xCell != null) {
						// 타입별로 내용 읽기
						switch (xCell.getCellType()) {

						case Cell.CELL_TYPE_BLANK:
							value = "";
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							value = xCell.getBooleanCellValue();
							break;
						case Cell.CELL_TYPE_ERROR:
							value = xCell.getErrorCellValue();
							break;
						case Cell.CELL_TYPE_FORMULA:
							value = xCell.getCellFormula();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if(DateUtil.isCellDateFormatted(xCell)) {
								Date dateCellValue = xCell.getDateCellValue();
								value = new SimpleDateFormat("yyyy-MM-dd").format(dateCellValue);
							} else {
								value = xCell.getNumericCellValue();
							}
							break;
						case Cell.CELL_TYPE_STRING:
							value = xCell.getStringCellValue();
							break;
							default:
						// for inspection
								}
							}
						}
						rowData.put(colNames[colIdx], value);
						if (value != null
								&& StringUtil.isNotEmptyStr(value.toString())) {
							notEmptyCnt++; // 빈값이 아닌것
						}
					}
					if (!rowData.isEmpty() && notEmptyCnt > 0) { // 빈로우 추가 안함
						list.add(rowData);
					}
				}
			}

		} else {
			logger.error(Utils.getMethodName(), "엑셀파일 로딩 실패");
		}

		return list;
	}


}
