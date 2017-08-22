package com.xupt.ssm.controller;

import com.xupt.ssm.controller.validation.ValidGroup1;
import com.xupt.ssm.exception.CustomException;
import com.xupt.ssm.po.ItemsCustom;
import com.xupt.ssm.po.ItemsQueryVo;
import com.xupt.ssm.service.ItemsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    //商品分类
    //itemsType表示最终的返回值放到request中的key，这里就是itemsTypes
    @ModelAttribute("itemsTypes")
    public Map<String,String> getItemsType(){
        Map<String,String> itemsTypes = new HashMap<String, String>();
        itemsTypes.put("101","数码");
        itemsTypes.put("102","母婴");
        return itemsTypes;
    }

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
    public ModelAndView editItems(@RequestParam(value = "id",required = true) Integer items_id)throws Exception{
        ModelAndView modelAndView = new ModelAndView();

        //调用service根据id查询商品信息
        ItemsCustom itemsCustom = itemsCustomService.findItemsById(items_id);
        //判断商品是否为空，根据id没查询到商品，抛出异常，提示商品信息不存在
        if(itemsCustom == null){
            throw new CustomException("商品不存在");
        }
        //将商品放到model
        modelAndView.addObject("items",itemsCustom);

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
    //在需要校验的pojo前边加 @Validated，在需要校验的pojo后边加BindingResult bindingResult接收校验出错信息
    //@Validated和BindingResult bindingResult是配对出现的，且形参顺序固定（一前一后）
    //@Validated(value = ValidGroup1.class)指定只用于ValidGroup1的校验
    //@ModelAttribute可以指定pojo回显到页面在request中的key，也就是这里的items
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(Model model,
                                  Integer id, HttpServletRequest request,
                                  @ModelAttribute("items") @Validated ItemsCustom itemsCustom,
                                  BindingResult bindingResult,
                                  MultipartFile items_pic//接收商品图片
                                  )
            throws Exception{
        //获取校验错误信息
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError:allErrors) {
                System.out.println(objectError.getDefaultMessage());
            }
            //将错误信息传到页面
            model.addAttribute("allErrors",allErrors);
            //直接使用model将提交的pojo回显到页面(这是最简单的回显方式，可以不用 @ModelAttribute)
            //简单类型的数据回显，使用model
            //model.addAttribute("id",id);
            model.addAttribute("items",itemsCustom);
            //出错重新到商品修改页面
            return "items/editItems";
        }

        //上传图片
        // 原始名称
        String orifinalFilename = items_pic.getOriginalFilename();
        if (items_pic != null){
            String pic_path = "D:\\一些图片\\";
            //新图片名称
            String newFilename = UUID.randomUUID() +
                    orifinalFilename.substring(orifinalFilename.lastIndexOf("."));
            //新图片
            File newFile = new File(pic_path + newFilename);

            //将内存中的数据写入磁盘
            items_pic.transferTo(newFile);
            //将新图片写到itemsCustom中
            itemsCustom.setPic(newFilename);
        }

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
        itemsCustomService.updateManyItems(itemsQueryVo);
        return "redirect:queryItems.action";
    }
}
