package li.newxm.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    private List<ArticleDTO> articleDTOS;
    //显示上一页
    private Boolean showPrevious;
    //显示下一页
    private Boolean showNext;
    //显示首页
    private Boolean showFirstPage;
    //显示尾页
    private Boolean showEnd;
    //当前页数
    private Integer currentPage;
    //总页数
    private  Integer totalpage;

    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {



        if(totalCount%size==0){
            totalpage = totalCount / size;
        }else{
            totalpage = totalCount / size + 1;
        }
        pages.add(page);
        for (int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalpage){
                pages.add(page+i);
            }
        }
        //页数范围限定
        if(page<1){
            page=1;
        }
        if (page>totalpage){
            page=totalpage;
        }

        this.currentPage= page;
        //判断首页是否显示
        if(pages.contains(1)){
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }

        //判断尾页是否需要显示
        if(pages.contains(totalpage)){
            showEnd=false;
        }else{
            showEnd=true;
        }

        //判断是否显示上一页
        if(page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }
        //判断是否显示下一页
        if( page==totalpage){
            showNext=false;
        }else{
            showNext=true;
        }

    }
}
