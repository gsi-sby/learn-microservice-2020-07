package org.ustadho.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ustadho.model.CatalogItem;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("catalog")
public class MoviceCatalogResource {

    @GetMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId) {
        return Collections.singletonList(
                new CatalogItem("Transformer", "test", 4)
        );
    }
}
