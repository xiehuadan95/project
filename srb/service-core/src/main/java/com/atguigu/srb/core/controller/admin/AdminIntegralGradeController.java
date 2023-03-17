package com.atguigu.srb.core.controller.admin;




import com.atguigu.srb.common.myassert.Assert;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.common.result.Result;
import com.atguigu.srb.core.mapper.IntegralGradeMapper;
import com.atguigu.srb.core.pojo.entity.IntegralGrade;
import com.atguigu.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author eric
 * @since 2022-03-19
 */
@Api(tags = "积分等级管理")
//跨域
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;
    @Autowired
    IntegralGradeMapper integralGradeMapper;


    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public Result listAll() {
        log.info("this is loginfo");
        log.warn("this is logwarn");
//        log.error("this is logerror");
        List<IntegralGrade> list = integralGradeService.list();
        return Result.ok().data("list",list).msg("获取列表成功");
    }

    //根据id删除积分等级 逻辑删除
    @ApiOperation(value="根据id删除数据记录",notes = "逻辑删除数据")
    @DeleteMapping("/remove/{id}")
    public Result removeById(
            @ApiParam(value="数据id",example = "100",required = true)
            @PathVariable long id) {
        boolean res = false;
        try {
            res = integralGradeService.removeById(id);

        } catch (Exception e) {
            return Result.error().msg("删除"+id+"出现异常："+e.getClass().getSimpleName());
        }
        if(res){
            return Result.ok().msg("删除"+id+"号数据成功！");
        }else {
            return Result.error().msg("删除"+id+"失败");
        }

    }
    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public Result save(
            @ApiParam(value="积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade){

        Assert.notNull(integralGrade.getBorrowAmount(), ResponseEnum.BORROW_AMOUNT_NULL_ERROR);

        boolean res = integralGradeService.save(integralGrade);
          if(res){
              return Result.ok().msg("保存成功");
          }else{
              return Result.error().msg("保存失败");
          }
    }
    @ApiOperation("根据id获取积分等级")
    @GetMapping("/get/{id}")
    public Result getById(
            @ApiParam(value="数据id",required = true)
            @PathVariable Long id){
        IntegralGrade res = integralGradeService.getById(id);
        if(res!=null){
            return Result.ok().data("record",res);
        }else {
            return Result.error().msg("数据获取失败");
        }
    }
    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public Result updateById(
            @ApiParam(value = "积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean res = integralGradeService.updateById(integralGrade);
        if(res){
            return Result.ok().msg("更新成功");
        }else {
            return Result.error().msg("数据更新失败");
        }

    }


    @GetMapping("/select")
    public void select(){

//      Map map = new HashMap();
//        List<IntegralGradeChild> list = integralGradeMapper.selectByMap(map);
//        System.out.println(list);
//        for (IntegralGradeChild integralGradeChild : list) {
//            System.out.println(integralGradeChild);
//        }



    }


}

