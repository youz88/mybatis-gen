#set(tableComment = table.getComment())
#set(entityClassName = table.buildEntityClassName())
#set(serviceVarName = firstCharToLowerCase(table.buildServiceClassName()))
package #(packageConfig.controllerPackage);

import #(packageConfig.servicePackage).#(table.buildServiceClassName());
import #(interfacePackageName).#(interfaceClassName);
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * #(tableComment) 控制层
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("#(table.buildControllerRequestMappingPrefix())/#(firstCharToLowerCase(entityClassName))")
public class #(table.buildControllerClassName()) implements #(interfaceClassName) {

    private final #(table.buildServiceClassName()) #(serviceVarName);

}
