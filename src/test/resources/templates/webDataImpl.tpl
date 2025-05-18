#set(clientVarName = firstCharToLowerCase(clientClassName))
package #(webDataImplPackageName);

#if(table.getGlobalConfig().isServiceGenerateEnable())
import #(webDataPackageName).#(webDataClassName);
#end
import #(clientPackageName).#(clientClassName);
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * #(table.getComment()) 数据层实现
 */
@Component
@RequiredArgsConstructor
public class #(webDataImplClassName) implements #(webDataClassName) {

    private final #(clientClassName) #(clientVarName);

}
