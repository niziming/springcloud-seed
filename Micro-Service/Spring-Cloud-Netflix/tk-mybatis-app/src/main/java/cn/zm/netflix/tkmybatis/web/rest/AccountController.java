package cn.zm.netflix.tkmybatis.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.common.base.ServiceException;
import cn.zm.common.base.TestObje;
import cn.zm.common.utils.ObjectsUtils;
import cn.zm.netflix.tkmybatis.web.entity.Account;
import cn.zm.netflix.tkmybatis.web.entity.dto.AccountDTO;
import cn.zm.netflix.tkmybatis.web.entity.vo.AccountVO;
import cn.zm.netflix.tkmybatis.web.mapper.AccountMapper;
import cn.zm.netflix.tkmybatis.web.service.IAccountService;
import cn.zm.tk.utils.ConvertUtil;
import cn.zm.tk.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import cn.zm.tk.base.BaseController;

import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.List;


/**
 * @author 十渊
 * @since 2021-10-13
 */
@RequestMapping("account")
@RestController
@Api(tags = "接口")
public class AccountController extends BaseController {
  @Resource
  private IAccountService accountService;

  @Value("${server.port}")
  private String port;

  @Resource
  private AccountMapper accountMapper;

  @GetMapping
  @ApiOperation("查询")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1"),
    @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10"),
    @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
    @ApiImplicitParam(name = "isDesc", value = "是否降序")
  })
  public ResResult<PageResult<AccountVO>> page(@Validated AccountDTO accountDTO) {
    // TODO 分页查询
    Page<AccountVO> page = getPage();
    List<Account> accounts = accountService.likeByProperty(accountDTO.convert());
    return ResResult.succ(
      PageResult.build(Objects.isNull(page) ? accounts : page)
    );
  }

  @GetMapping("{id}")
  @ApiOperation("查询(id)")
  public ResResult<AccountVO> get(@PathVariable String id) {
    // TODO 查询
    return ResResult.succ(Objects.nonNull(accountService.selectById(id)) ? accountService.selectById(id).convert() : null);
  }

  @PostMapping
  @ApiOperation("新增(list)")
  public ResResult add(@RequestBody @Validated List<AccountDTO> accountDTOs) {
    // TODO 新增
    accountDTOs = accountDTOs.stream().map(c -> c.setId(null)).collect(Collectors.toList());
    accountService.saveBatch(ConvertUtil.convertList(accountDTOs));
    return ResResult.succ("新增成功");
  }

  @DeleteMapping("{id}")
  @ApiOperation("删除")
  public ResResult del(@PathVariable String id) {
    // TODO 删除
    accountService.deleteById(id);
    return ResResult.succ("删除成功");
  }

  @PutMapping
  @ApiOperation("修改")
  public ResResult update(@RequestBody @Validated AccountDTO accountDTO) {
    // TODO 修改
    accountService.updateSelectiveById(accountDTO.convert());
    return ResResult.succ("修改成功");
  }

  @GetMapping("ribbon/service")
  @ApiOperation("负载均衡测试")
  public ResResult ribbonService() {
    // TODO 负载均衡测试
    return ResResult.succ("tkapp:" + port);
  }

  @GetMapping("test")
  @ApiOperation("测试树结构")
  public ResResult<List<TestObje>> test() {
    TestObje build = TestObje.builder().build();
    TestObje build1 = TestObje.builder().build();
    TestObje build2 = TestObje.builder().build();
    ArrayList<TestObje> testObjes = new ArrayList<>();

    build.setId("1");
    build.setPid("0");
    build.setName("parent");

    build1.setId("2");
    build1.setPid("1");
    build1.setName("child");

    build2.setId("3");
    build2.setPid("2");
    build2.setName("child");

    testObjes.add(build);
    testObjes.add(build1);
    testObjes.add(build2);
    List<TestObje> testObjes1 = ObjectsUtils.list2Tree(t -> t.getPid(), testObjes, "0");
    // TODO 负载均衡测试
    return ResResult.succ(testObjes1);
  }

  @PostMapping("/upload")
  @ApiOperation("上传文件")
  public void upload(@RequestParam(value = "upload", required = false) MultipartFile multipartFile) {
    try {
      multipartFile.transferTo(Paths.get(multipartFile.getResource().getFilename()));
    } catch (IOException e) {
      throw new ServiceException("导出失败");
    }
  }

  @PostMapping("/export")
  @ApiOperation("导出")
  public void export(HttpServletResponse res) {
    String filename = "README.md";
    File file = new File(filename);
    // res.setContentType("application/octet-stream");
    // res.setHeader("content-type", "application/octet-stream");
      res.setContentType("multipart/form-data");
      res.setHeader("content-type", "multipart/form-data");

      try {
      res.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "utf8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    byte[] buffer = new byte[1024];
    // 输出流
    OutputStream os = null;
    try (FileInputStream fis = new FileInputStream(file);
         BufferedInputStream bis = new BufferedInputStream(fis);) {
      os = res.getOutputStream();
      int i = bis.read(buffer);
      while (i != -1) {
        os.write(buffer);
        i = bis.read(buffer);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
