#set(excludeImports = [
  "com.mybatisflex.annotation.KeyType",
  "com.mybatisflex.annotation.Id",
  "java.io.Serializable"
])
package #(dtoPackageName);

#for(importClass : table.buildImports(true))
    #if(!excludeImports.contains(importClass))
import #(importClass);
    #end
#end
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * #(table.getComment()) DTO
 */
@Data
@Accessors(chain = true)
@Schema(description = "#(table.getComment())DTO")
public class #(dtoClassName) {

#for(column : table.columns)
    #set(comment = javadocConfig.formatColumnComment(column.comment))
    @Schema(description = "#(comment)")
    private #(column.propertySimpleType) #(column.property)#if(hasText(column.propertyDefaultValue)) = #(column.propertyDefaultValue)#end;

#end
}
