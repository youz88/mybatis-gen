#set(dataVarName = firstCharToLowerCase(webDataClassName))
package #(webServiceImplPackageName);

#if(table.getGlobalConfig().isServiceGenerateEnable())
import #(webServicePackageName).#(table.buildServiceClassName());
#end
import #(webDataPackageName).#(webDataClassName);
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * #(table.getComment()) 服务层实现
 */
@Service
@RequiredArgsConstructor
public class #(table.buildServiceImplClassName()) implements #(table.buildServiceClassName()) {

    private final #(webDataClassName) #(dataVarName);

}
