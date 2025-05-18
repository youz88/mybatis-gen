#set(tableComment = table.getComment())
#set(entityClassName = table.buildEntityClassName())
#set(serviceVarName = firstCharToLowerCase(table.buildServiceClassName()))
package #(webControllerPackageName);

import #(webServicePackageName).#(table.buildServiceClassName());
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * #(tableComment) 控制层
 */
@Tag(name = "#(tableComment)接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("#(table.buildControllerRequestMappingPrefix())/#(firstCharToLowerCase(entityClassName))")
public class #(table.buildControllerClassName()) {

    private final #(table.buildServiceClassName()) #(serviceVarName);

}
