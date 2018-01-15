package com.spike.controller;

import com.spike.dto.Exposer;
import com.spike.dto.SpikeExecution;
import com.spike.dto.SpikeResult;
import com.spike.pojo.Spike;
import com.spike.service.SpikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author wangxu
 *         blog：http://www.cnblogs.com/wxisme/
 *         github：https://github.com/wxisme
 */
@Controller
@RequestMapping("/spike") // url:/模块/资源/{id}/细分
public class SpikeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SpikeService spikeService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Spike> list = spikeService.getSpikeList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{spikeId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("spikeId") Long spikeId, Model model) {
        if (spikeId == null) {
            return "redirect:/spike/list";
        }
        Spike spike = spikeService.getSpikeById(spikeId);
        if (spike == null) {
            return "forward:/spike/list";
        }
        model.addAttribute("spike", spike);
        return "detail";
    }

    @RequestMapping(value = "/{spikeId}/exposer", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody   //把结果封装成json
    public SpikeResult<Exposer> exposer(@PathVariable Long spikeId) {
        SpikeResult<Exposer> result;

        try {
            Exposer exposer = spikeService.exportSpikeUrl(spikeId);
            result = new SpikeResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SpikeResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{spikeId}/{md5}/execution", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SpikeResult<SpikeExecution> execute(@PathVariable("spikeId") Long spikeId, @PathVariable("md5") String md5,
                                               @CookieValue(value = "userPhone", required = false) String userPhone) {
        if (userPhone == null) {
            return new SpikeResult<SpikeExecution>(false, "Login first.");
        }
        SpikeResult<SpikeExecution> result = null;

        try {
            SpikeExecution execution = spikeService.executeSpike(spikeId, userPhone, md5);
            result.setData(execution);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setError(e.getMessage());
        }
        return result;
    }


    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SpikeResult<Long> now(HttpServletResponse resp) throws IOException {
        return new SpikeResult<Long>(true, (new Date()).getTime());
    }

}
