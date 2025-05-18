#set(mapperVarName = firstCharToLowerCase(table.buildMapperClassName()))
package #(dataImplPackageName);

#if(table.getGlobalConfig().isServiceGenerateEnable())
import #(dataPackageName).#(dataClassName);
#end
import #(packageConfig.mapperPackage).#(table.buildMapperClassName());
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * #(table.getComment()) 数据层实现
 */
@Component
@RequiredArgsConstructor
public class #(dataImplClassName) implements #(dataClassName) {

    private final #(table.buildMapperClassName()) #(mapperVarName);

}
