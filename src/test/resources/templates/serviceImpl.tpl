#set(dataVarName = firstCharToLowerCase(dataClassName))
package #(packageConfig.serviceImplPackage);

#if(table.getGlobalConfig().isServiceGenerateEnable())
import #(packageConfig.servicePackage).#(table.buildServiceClassName());
#end
import #(dataPackageName).#(dataClassName);
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * #(table.getComment()) 服务层实现
 */
@Service
@RequiredArgsConstructor
public class #(table.buildServiceImplClassName()) implements #(table.buildServiceClassName()) {

    private final #(dataClassName) #(dataVarName);

}
