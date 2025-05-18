#set(entityClassName = table.buildEntityClassName())
package #(clientPackageName);

import #(interfacePackageName).#(interfaceClassName);
import org.springframework.cloud.openfeign.FeignClient;

/**
 * #(table.getComment()) 客户端
 */
@FeignClient(value = "#(feignValue)", contextId = "#(modelName)-#(firstCharToLowerCase(entityClassName))", path = "/#(firstCharToLowerCase(entityClassName))")
public interface #(clientClassName) extends #(interfaceClassName) {

}
