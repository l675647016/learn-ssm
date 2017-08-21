package com.xupt.ssm.controller;

import com.xupt.ssm.po.ItemsCustom;
import com.xupt.ssm.po.ItemsQueryVo;
import com.xupt.ssm.service.ItemsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 梁峻磊 on 2017/8/16.
 */
@Controller
//为了对url进行分类管理，可以在这里定义根路径，最终路径：根路径+子路径
//eg.  商品列表  /items/queryItems.action
@RequestMapping("/items")
public class ItemsCustomController {

    @Autowired
    private ItemsCustomService itemsCustomService;

    @RequestMapping("/queryItems")
    public ModelAndView findItemsList(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception{
        //输出共享request
        System.out.println(request.getParameter("id"));
        List<ItemsCustom> itemsList = itemsCustomService.findItemsList(itemsQueryVo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        modelAndView.setViewName("items/itemsList");
        return modelAndView;
    }

    //商品信息的修改的展示
    //限制http请求方法
    //返回modelAnView
    @RequestMapping(value = "/editItems",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView editItems()throws Exception{
        ModelAndView modelAndView = new ModelAndView();

        //调用service根据id查询商品信息
        ItemsCustom itemsCustom = itemsCustomService.findItemsById(1);

        //将商品放到model
        modelAndView.addObject("itemsCustom",itemsCustom);

        //商品修改页面
        modelAndView.setViewName("items/editItems");

        return modelAndView;
    }
    //返回String
    /*@RequestMapping(value = "/editItems",method = {RequestMethod.POST,RequestMethod.GET})
    //@RequestParam里面指定request传入参数名称和形参进行绑定
    //required属性指定参数是否要传入
    //defaultValue可以设置默认值，如果id参数没有传入，将默认值与形参绑定
    public String editItems(Model model,
                            @RequestParam(value = "id",defaultValue = "1")
                                    Integer items_id)
            throws Exception{

        //调用service根据id查询商品信息
        ItemsCustom itemsCustom = itemsCustomService.findItemsById(items_id);
        model.addAttribute("itemsCustom",itemsCustom);
        return "items/editItems";
    }*/



    /**
     * 商品信息修改提交
     * @return ModelAndView
     * @throws Exception
     */
    /*@RequestMapping("/editItemsSubmit")
    public ModelAndView editItemsSubmit()throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }*/

    /**
     * 商品信息修改提交
     * @return String
     * @throws Exception
     */
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(Integer id,HttpServletRequest request,
                                  @RequestParam(value = "itemsCustom") ItemsCustom itemsCustom)
            throws Exception{
        //调用service跟新商品信息，页面需要将商品信息传到此方法
        itemsCustomService.updateItems(id,itemsCustom);

        //重定向：地址栏url会改变，request不共享
        //return "redirect:queryItems.action";
        //请求转发，地址栏url不会改变，共享request
        return "forward:queryItems.action";
        //return "success";
    }

    /**
     * 批量删除商品
     * @param items_id 商品id数组
     * @return String
     * @throws Exception
     */
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] items_id)throws Exception{

        //调用service批量删除
        itemsCustomService.deleteItems(items_id);

        /*ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/itemsList");
        return modelAndView;*/
        return "redirect:queryItems.action";
    }

    //批量修改商品提交
    //通过ItemsQueryVo接收批量提交的商品信息，将商品信息存储到ItemsQueryVo 中的itemsList中。
    @RequestMapping("/editItemsQuery")
    public ModelAndView editItemsQuery(ItemsQueryVo itemsQueryVo) throws Exception{
        List<ItemsCustom> itemsList = itemsCustomService.findItemsList(itemsQueryVo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        modelAndView.setViewName("items/editItemsQuery");
        return modelAndView;
    }

    //批量修改商品页面，将商品信息查出来，在页面中可以编辑商品信息
    @RequestMapping("/editItemsAllSubmit")
    public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception{
        return "success";
    }
}
