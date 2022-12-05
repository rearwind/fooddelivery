package fooddelivery.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

// @FeignClient(name = "store", url = "${api.url.store}", fallback = CookingServiceImpl.class)
@FeignClient(name = "store", url = "${api.url.store}")
public interface CookingService {
    @RequestMapping(method= RequestMethod.GET, path="/cookings/{id}")
    public Cooking getCooking(@PathVariable("id") Long id);
}

