package com.zfsoft.common;



import java.util.List;


@SuppressWarnings("rawtypes")
public class Pagination {

    /**
     * 当前页结果集
     */
    private List datas;
    /**
     * 总记录数
     */
    private int totalRows = 0;
    /**
     * 最大页数
     */
    private int maxPageNumber = 1;
    /**
     * 页码
     */
    private Integer pageNumber = 1;

    /**
     * 分页是否统计总记录
     */
    private boolean pageTotalCount = true;
    /**
     * 每页显示数量
     */
    private Integer pageSize = 10;

    public Pagination(List result) {
        setDatas(result);
    }

    /**
     * 初始化分页对象
     * 
     * @param result
     *            结果集
     * @param totalRows
     *            总记录数
     * @param pageNumber
     *            当前页数
     * @param pageSize
     *            每页显示数量
     */
    public Pagination(List result, int totalRows, int pageNumber,
                      int pageSize) {
        setDatas(result);
        setTotalRows(totalRows);
        setMaxPageNumber((totalRows - 1) / pageSize + 1);
        if (pageNumber > maxPageNumber)
            setPageNumber(maxPageNumber);
        else
            setPageNumber(pageNumber);
        setPageSize(pageSize);
    }
    
    public Pagination(List result, int totalRows, int pageNumber, int pageSize,
                      boolean pageTotalCount) {
        setDatas(result);
        if (pageTotalCount) {
            setTotalRows(totalRows);
            setMaxPageNumber((totalRows - 1) / pageSize + 1);
            if (pageNumber > maxPageNumber)
                setPageNumber(maxPageNumber);
            else
                setPageNumber(pageNumber);
            setPageSize(pageSize);
        } else {
            setPageNumber(pageNumber);
            setPageSize(pageSize);
        }
        this.pageTotalCount = pageTotalCount;
    }

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }

    public void removeData(Object obj) {
        datas.remove(obj);
    }

    public void clear() {
        datas.clear();
    }

    /**
     * 获取页码
     * 
     * @return pageNumber 页码
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * 设置页码
     * 
     * @param pageNumber
     *            页码
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 获取每页显示数量
     * 
     * @return pageSize 每页显示数量
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页显示数量
     * 
     * @param pageSize
     *            每页显示数量
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取最大页数
     * 
     * @return maxPageNumber 最大页数
     */
    public int getMaxPageNumber() {
        return maxPageNumber;
    }

    /**
     * 设置最大页数
     * 
     * @param maxPageNumber
     *            最大页数
     */
    public void setMaxPageNumber(int maxPageNumber) {
        this.maxPageNumber = maxPageNumber;
    }

    /**
     * 获取总记录数
     * 
     * @return totalRows 总记录数
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * 设置总记录数
     * 
     * @param totalRows
     *            总记录数
     */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    /**
     * 获取分页是否统计总记录
     * 
     * @return 分页是否统计总记录
     */
    public boolean isPageTotalCount() {
        return pageTotalCount;
    }

    /**
     * 设置分页是否统计总记录
     * 
     * @param pageTotalCount
     *            分页是否统计总记录
     */
    public void setPageTotalCount(boolean pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }
}
