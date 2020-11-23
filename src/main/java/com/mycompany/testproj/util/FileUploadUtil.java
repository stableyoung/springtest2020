package com.mycompany.testproj.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.testproj.domain.UploadedFile;

/**
 * 파일 업로드 유틸
 * @author YongJin
 *
 */
public class FileUploadUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

	String baseDir = ""; //"C:/Temp/uploaded";
	String subDir;

	int maxFileSize = 0;
	String allowedExtentions;
	String restrictedExtentions;
	String basicRestrictedExtentions = "js,exe,bat,sh";

	List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();

	public FileUploadUtil(String subDir){
		String inSubDir = StringUtils.trim(subDir);
		if (!StringUtils.startsWithIgnoreCase(inSubDir, "/")){
			inSubDir = "/" + inSubDir;
		}
		this.subDir = inSubDir;
	}

	public List<UploadedFile> getUploadedFiles() {
		return uploadedFiles;
	}

	public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

	public String getSubDir() {
		return subDir;
	}

	public void setSubDir(String subDir) {
		this.subDir = subDir;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public int getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(int maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public String getRestrictedExtentions() {
		return restrictedExtentions;
	}

	public void setRestrictedExtentions(String restrictedExtentions) {
		this.restrictedExtentions = restrictedExtentions;
	}

	public String getBasicRestrictedExtentions() {
		return basicRestrictedExtentions;
	}

	public String getAllowedExtentions() {
		return allowedExtentions;
	}

	public void setAllowedExtentions(String allowedExtentions) {
		this.allowedExtentions = allowedExtentions;
	}
	/**
	 * 최대 파일 사이즈 체크
	 * @param mf
	 * @throws Exception
	 */
	public void checkMaxFileSize(MultipartFile mf) throws Exception{
		if (mf != null && !mf.isEmpty() && maxFileSize != 0){
			if (mf.getSize() > maxFileSize){
				double max = NumberUtils.toDouble(maxFileSize+"");
				double kb = Math.round(max/1024*100) / 100d;
				throw new Exception(mf.getOriginalFilename() + " 파일의 크기가 최대 허용가능한 파일크기["+NumberFormat.getInstance().format(kb)+"kb]보다 큽니다.");
			}
		}
	}

	/**
	 * 가능 및 불가능한 확장자 체크
	 * @param mf
	 * @throws Exception
	 */
	public void checkExtention(MultipartFile mf) throws Exception{
		String fileName = "";
		if(mf != null) {
			fileName = mf.getOriginalFilename();
		}
		// 기본 불가능한 확장자인지
		if (StringUtils.isNotEmpty(this.getBasicRestrictedExtentions())){
			if ( StringUtils.containsIgnoreCase(this.getBasicRestrictedExtentions(), FilenameUtils.getExtension(fileName))){
				throw new Exception(fileName + " 파일은 업로드 가능한 파일유형["+this.getBasicRestrictedExtentions()+"]이 아닙니다.");
			}
		}
		// 설정한 가능한 확장자에 속하는 지
		if (StringUtils.isNotEmpty(this.getAllowedExtentions())){
			if (! StringUtils.containsIgnoreCase(this.getAllowedExtentions(), FilenameUtils.getExtension(fileName))){
				throw new Exception(fileName + " 파일은 업로드 가능한 파일유형["+this.getAllowedExtentions()+"]이 아닙니다.");
			}
		}
		// 설정한 불가능한 확장자인지
		if (StringUtils.isNotEmpty(this.getRestrictedExtentions())){
			if ( StringUtils.containsIgnoreCase(this.getRestrictedExtentions(), FilenameUtils.getExtension(fileName))){
				throw new Exception(fileName + " 파일은 업로드 가능한 파일유형["+this.getRestrictedExtentions()+"]이 아닙니다.");
			}
		}
	}

	public String getSaveDir(){
		return this.getBaseDir() + this.getSubDir();
	}

}
