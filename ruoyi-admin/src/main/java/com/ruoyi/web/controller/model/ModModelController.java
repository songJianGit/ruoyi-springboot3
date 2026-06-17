package com.ruoyi.web.controller.model;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.service.domain.ModModel;
import com.ruoyi.service.service.IModModelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 模型数据列Controller
 *
 * @author ruoyi
 * @date 2026-06-17
 */
@RestController
@RequestMapping("/system/MODEL")
public class ModModelController extends BaseController {
    @Autowired
    private IModModelService modModelService;

    /**
     * 查询模型数据列列表
     */
    @PreAuthorize("@ss.hasPermi('system:MODEL:list')")
    @GetMapping("/list")
    public TableDataInfo list(ModModel modModel) {
        startPage();
        List<ModModel> list = modModelService.list(modModel.toQuery());
        return getDataTable(list);
    }

    /**
     * 导出模型数据列列表
     */
//    @PreAuthorize("@ss.hasPermi('system:MODEL:export')")
    @Log(title = "模型数据列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ModModel modModel) {
        List<ModModel> list = modModelService.list(modModel.toQuery());
        ExcelUtil<ModModel> util = new ExcelUtil<ModModel>(ModModel.class);
        util.exportExcel(response, list, "模型数据列数据");
    }

    /**
     * 获取模型数据列详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:MODEL:query')")
    @GetMapping(value = "/{ID}")
    public AjaxResult getInfo(@PathVariable("ID") String ID) {
        return success(modModelService.getById(ID));
    }

    /**
     * 新增模型数据列
     */
//    @PreAuthorize("@ss.hasPermi('system:MODEL:add')")
    @Log(title = "模型数据列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ModModel modModel) {
        modModel.setStatus(1);
        return toAjax(modModelService.save(modModel));
    }

    /**
     * 修改模型数据列
     */
//    @PreAuthorize("@ss.hasPermi('system:MODEL:edit')")
    @Log(title = "模型数据列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ModModel modModel) {
        return toAjax(modModelService.updateById(modModel));
    }

    /**
     * 删除模型数据列
     */
//    @PreAuthorize("@ss.hasPermi('system:MODEL:remove')")
    @Log(title = "模型数据列", businessType = BusinessType.DELETE)
    @DeleteMapping("/{IDs}")
    public AjaxResult remove(@PathVariable String[] IDs) {
        LambdaUpdateWrapper<ModModel> up = Wrappers.lambdaUpdate();
        up.set(ModModel::getStatus, 0);
        up.in(ModModel::getId, List.of(IDs));
        return toAjax(modModelService.update(up));
    }
}
