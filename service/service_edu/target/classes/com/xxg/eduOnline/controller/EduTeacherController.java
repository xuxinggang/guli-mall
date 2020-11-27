package com.xxg.eduOnline.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.entity.EduTeacher;
import com.xxg.eduOnline.entity.vo.TeacherQueryWrapper;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.eduOnline.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师
 * </p>
 * @author xxg.testJava
 * @since 2020-07-13
 */
@Api(description ="讲师管理")
@RestController//@response:返回一个json数据
@RequestMapping("/eduService/teacher")
@CrossOrigin//开启跨域：浏览器从一个域名的网页去请求另一个域名的资源时，域名、端口、协议任一不同，都是跨域，使用Ajax来解决跨域问题
public class EduTeacherController {
    //查询讲师表的所有数据
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/findAll")
    @ApiOperation(value = "所有讲师列表")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        try {
            //int i = 10 / 0;//自定义异常
        }catch (Exception e){
            //自定义的异常，不会主动抛出，需要自己手动抛出异常
            throw new DiyException(10001,"出现自定义DiyException类异常");
        }
//        return R.success().data((Map<String, Object>) list);
        return R.success().data("AllDataForTeacher",list);  //链式编程
}
    @ApiOperation(value = "根据id逻辑删除讲师")
    @DeleteMapping("{id}")
    public R deleteById(@ApiParam(name="id",value = "讲师ID",required = true) @PathVariable("id") String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return R.success();
        }else {
            return R.error();
        }
    }
    /**
     * 讲师模块分页查询(restful风格)
     * @param current:当前页
     * @param limit：每页记录数
     * @return
     */
    @GetMapping("/pageTeacherList/{current}/{limit}")
    public R pageListTeacher(@PathVariable("current") long current,@PathVariable("limit") long limit){
        //创建分页page对象（这里传的包一定要是mp的包）
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        //调用分页方法:原理,底层封装，把分页需要的所有数据，全部封装到teacherPage中
        IPage<EduTeacher> page = eduTeacherService.page(teacherPage, null);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        //1、通过链式编程来设置
//        return R.success().data("total",total).data("records",records);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("records",records);
        return R.success().data(map);
    }

    /**
     * @RequestBody(required = false):表示该参数可以为空，默认为true
     * 使用json格式来传递数据，
     * 并且把json数据封装到对应的对象里面去，
     * 需要用Post()方式进行提交
     *                               {
     *                                   "name":"王","level":1......
     *                               }
     * @param current
     * @param limit
     * @param teacherQueryWrapper
     * @return
     */
    @PostMapping("pageTeacherWrapper/{current}/{limit}")
    public R pageTeacherWrapper(@PathVariable("current") long current,
                                @PathVariable("limit") long limit,
                                @RequestBody(required = false) TeacherQueryWrapper teacherQueryWrapper){
        //获取分页page对象
        Page<EduTeacher> eduTeacherPage = new Page<>(current,limit);
        //构建wrapper条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询，进行判断，在进行条件拼接
        String name = teacherQueryWrapper.getName();
        Integer level = teacherQueryWrapper.getLevel();
        String begin = teacherQueryWrapper.getBegin();
        String end = teacherQueryWrapper.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }
        //对查出来的数据进行排序
        wrapper.orderByDesc("gmt_create");
        //根据条件进行分页查询
        eduTeacherService.page(eduTeacherPage,wrapper);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        return R.success().data("total",total).data("records",records);
    }
    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.success();
        }else
          return R.error();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("findTeacherById/{id}")
    public R findTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true)@PathVariable("id") String id){
        EduTeacher byId = eduTeacherService.getById(id);
        return R.success().data("findTeacherById",byId);
    }
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("deleteTeacherById/{id}")
    public R deleteTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true)@PathVariable("id")String id){
        boolean delete= eduTeacherService.removeById(id);
        if (delete){
            return R.success();
        }
        return R.error();
    }
    //根据id修改讲师内容
    @ApiOperation(value = "根据id修改讲师内容")
    @PutMapping("updateTeacherById/{id}")
    public R updateTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable("id") String id,
                               @ApiParam(name = "teacher", value = "讲师对象", required = true) @RequestBody EduTeacher eduTeacher){
        eduTeacher.setId(id);
        boolean updateById = eduTeacherService.updateById(eduTeacher);
        if (updateById){
            return R.success();
        }
        return R.error();
    }
}

