package #(packageConfig.mapperPackage);

import org.apache.ibatis.annotations.Mapper;
import #(mapperConfig.buildSuperClassImport());
import #(packageConfig.entityPackage).#(table.buildEntityClassName());

/**
 * #(table.getComment()) 映射层
 */
@Mapper
public interface #(table.buildMapperClassName()) extends #(mapperConfig.buildSuperClassName())<#(table.buildEntityClassName())> {

}
