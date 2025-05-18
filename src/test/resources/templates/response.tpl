#set(excludeImports = [
  "com.mybatisflex.annotation.KeyType",
  "com.mybatisflex.annotation.Id",
  "java.io.Serializable"
])
package #(responsePackageName);

#for(importClass : table.buildImports(true))
    #if(!excludeImports.contains(importClass))
import #(importClass);
    #end
#end
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * #(table.getComment()) 返回参数
 */
@Data
@Accessors(chain = true)
public class #(responseClassName) {

#for(column : table.columns)
    #set(comment = javadocConfig.formatColumnComment(column.comment))
    #if(hasText(comment))
    /**
     * #(comment)
     */
    #end
    private #(column.propertySimpleType) #(column.property)#if(hasText(column.propertyDefaultValue)) = #(column.propertyDefaultValue)#end;

#end
}
