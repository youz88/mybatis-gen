package #(entityPackageName);

import com.mybatisflex.annotation.Column;
#for(importClass : table.buildImports(isBase))
import #(importClass);
#end
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;
import lombok.experimental.Accessors;

#if(!isBase)
/**
 * #(table.getComment()) 实体类
 */
@Data
@Accessors(chain = true)
#(table.buildTableAnnotation())#end
public class #(entityClassName) implements Serializable {

    private static final long serialVersionUID = 1L;

#for(column : table.columns)
    #set(comment = javadocConfig.formatColumnComment(column.comment))
    #if(hasText(comment))
    /**
     * #(comment)
     */
    #end
    #if(column.property == "id")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    #else
    #if(column.property == "deleted")
    @Column(isLogicDelete = true)
    #else
    #set(annotations = column.buildAnnotations())
    #if(hasText(annotations))
    #(annotations)
    #end
    #end
    #end
    private #(column.propertySimpleType) #(column.property)#if(hasText(column.propertyDefaultValue)) = #(column.propertyDefaultValue)#end;

#end
}
