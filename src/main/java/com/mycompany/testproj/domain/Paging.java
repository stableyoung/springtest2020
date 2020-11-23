package com.mycompany.testproj.domain;


/**
 * <PRE>
 * Paging 정보를 담는 모델.
 * <br />
 * Author : "Yongjae Jang"
 * <br />
 * Date   : 2012. 5. 22. 오후 2:50:43
 * <br />
 * History
 * ------------------------------------------------------
 * 2012. 5. 22. / jackey : 신규 개발.
 * </PRE>
 */
public class Paging extends BaseVO {

    /**
     *
     */
    private static final long serialVersionUID = 5246946100739071237L;
    private static final int		DEFAULT_PAGE_SIZE	= 10;

    private int					currentPage			= 1;
    /**
     * 한 페이지에서 보여줄 아이템 수
     */
    private int					perPage				= DEFAULT_PAGE_SIZE;

    /**
     * 페이지 네비게이션에서 한 화면에 보여줄 페이지 수
     */
    private int					perPageNavi			= DEFAULT_PAGE_SIZE;
    private int					itemCount			= 0;

    /**
     * 전체 페이지 수
     */
    private int					pageCount;

    /**
     * Default Constructor.
     */
    public Paging() {
    }

    public Paging(int itemCount) {
        this.itemCount = (itemCount < 0) ? 0 : itemCount;
    }

    public Paging(int perPage, int itemCount, int current) {
        paginate(perPage, perPageNavi, itemCount, current);
    }

    public Paging(int perPage, int perPageNavi, int itemCount, int current) {
        paginate(perPage, perPageNavi, itemCount, current);
    }

    public Paging(int itemCount, BaseSC search) {
        if(search != null) {
            paginate(search.getItemPerPage(), search.getPagePerChapter(), itemCount, search.getCurrentPage());
        }
    }

    /**
     * Request 객체에서 paging 정보를 가져와 Paging 객체를 생성.
     *
     * @param request
     */
//	public Paging(HttpServletRequest req) {
//		this(req, "currentPage", "itemCount", "perPage");
//	}
//
//	public Paging(HttpServletRequest req, String currentPageName, String itemCountName, String perPageName) {
//		super();
//		setCurrentPage(requestToInteger(req, currentPageName));
//		setItemCount(requestToInteger(req, itemCountName));
//		setPerPage(requestToInteger(req, perPageName));
//	}

    public int getPageCount() {
        return getLast();
    }

//	private int requestToInteger(HttpServletRequest req, String key) {
//		return Integer.parseInt(req.getParameter(key));
//	}

    public boolean notInited() {
        return itemCount != 0 && perPage != 0 && currentPage != 0;
    }

    /**
     * 페이징 정보를 갱신한다.
     */
    private void paginate(int perPage, int perPageNavi, int itemCount, int current) {
        this.perPage = (perPage <= 0) ? DEFAULT_PAGE_SIZE : perPage;
        this.perPageNavi = (perPage <= 0) ? DEFAULT_PAGE_SIZE : perPageNavi;
        this.itemCount = (itemCount < 0) ? 0 : itemCount;
        this.pageCount = getLast();
        this.currentPage = (current < 1) ? 1 : (current > pageCount) ? pageCount : current;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int current) {
        this.currentPage = (current < 1) ? 1 : current;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = (perPage <= 0) ? DEFAULT_PAGE_SIZE : perPage;
    }

    public int getPerPageNavi() {
        return perPageNavi;
    }

    public void setPerPageNavi(int perPageNavi) {
        this.perPageNavi = (perPageNavi <= 0) ? DEFAULT_PAGE_SIZE : perPageNavi;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = (itemCount < 0) ? 0 : itemCount;
    }

    public int getFirst() {
        return 1;
    }

    public int getLast() {
        return getLastPage();
    }

    public int getPrev() {
        return (currentPage > 1) ? currentPage - 1 : 1;
    }

    public int getNext() {
        int pageCount = getLast();
        return (currentPage < pageCount) ? currentPage + 1 : pageCount;
    }

    public int getFirstItemNo() {
        return (currentPage - 1) * perPage + 1;
    }

    public int getLastItemNo() {
        return currentPage * perPage;
    }

    public boolean hasPrevChapter() {
        return currentPage > perPageNavi;
    }

    public int getPrevChapter() {
        return (hasPrevChapter()) ? currentPage - perPageNavi : 1;
    }

    public boolean hasNextChapter() {
        int pageCount = getLast();
        return currentPage + perPageNavi < pageCount;
    }

    public int getNextChapter() {
        int pageCount = getLast();
        return (currentPage + perPageNavi < pageCount) ? currentPage + perPageNavi : pageCount;
    }

    public int getChapterFirstPage() {
        if (currentPage < perPageNavi){
            return 1;
        }else if(currentPage % perPageNavi == 0){
            return (currentPage - perPageNavi)+1;
        }else{
            return  startPosOfChapter() + 1;
        }
    }

    public int getChapterLastPage() {
        int chapterLastPage = getChapterFirstPage() + perPageNavi - 1;
        if (chapterLastPage > getLast()) {
            return getLast();
        }
        else {
            return chapterLastPage;
        }
//		if(currentPage ==  perPage){
//			return currentPage;
//		}else if (currentPage+perPage > getLast()){
//			return getLast();
//		}else{
//			return  startPosOfChapter()+perPage;
//		}
    }

    private int startPosOfChapter() {
        return ((int)(currentPage / perPageNavi))*perPageNavi;
    }

    public int getLastPage(){
        if(itemCount>perPage){
            if(itemCount%perPage==0){
                return itemCount/perPage;
            }else{
                return (itemCount/perPage)+1;
            }
        }else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Paging [current=" + currentPage + ", itemCount=" + itemCount + ", perPage=" + perPage + ", perPageNavi=" + perPageNavi + "]";
    }

    /**
     * Paging 시 필요한 파라미터값을 생성.
     *
     * @param pageNo
     *
     * @return
     */
    public String getURLTokens(int pageNo) {
        return "currentPage=" + pageNo + "&itemCount=" + getItemCount() + "&perPage=" + getPerPage() + "&perPageNavi=" + getPerPageNavi();
    }

    /**
     * 정보를 갱신한다.
     */
    public void setup(int total, int perPage2, int currentPage2) {
        setItemCount(total);
        setPerPage(perPage2);
        setCurrentPage(currentPage2);
    }

    /**
     * 페이지 정보 수정
     *
     * Date   : 2013.04.10
     * Author : Kim Tae Hun
     */

    public void reload(int currentPage){

        setCurrentPage(currentPage);
        paginate(perPage, perPageNavi, itemCount, currentPage);
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }



    public static int defaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }
}
