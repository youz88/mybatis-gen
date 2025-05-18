#set(excludeImports = [
  "com.mybatisflex.annotation.KeyType",
  "com.mybatisflex.annotation.Id",
  "java.io.Serializable"
])
#set(hasLongType = false)
#for(column : table.columns)
  #if(column.propertySimpleType == "Long")
    #set(hasLongType = true)
    #break
  #end
#end
package #(voPackageName);

#if(hasLongType)
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
#end
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
@Schema(description = "#(table.getComment())VO")
public class #(voClassName) {

#for(column : table.columns)
    #set(comment = javadocConfig.formatColumnComment(column.comment))
    #if(column.propertySimpleType == "Long")
    @JsonSerialize(using = ToStringSerializer.class)
    #end
    @Schema(description = "#(comment)")
    private #(column.propertySimpleType) #(column.property)#if(hasText(column.propertyDefaultValue)) = #(column.propertyDefaultValue)#end;

#end
}
