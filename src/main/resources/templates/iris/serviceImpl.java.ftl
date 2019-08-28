package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * ${table.comment!} 服务实现类 自定义
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@Slf4j
@Transactional
public class ${table.serviceImplName} extends BaseServiceImpl implements ${table.serviceName}, IRISConstants {

}
