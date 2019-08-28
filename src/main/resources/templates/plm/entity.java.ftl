package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
import com.ithinkdt.web.common.dto.PageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * ${table.comment!} PLM
 * @author ${author}
 * @since ${date}
 *
*/
@Getter
@Setter
public class ${entity} extends PageEntity {

<#if entitySerialVersionUID>
    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.comment!?length gt 0>
    /**
    * ${field.comment}
    */
    @ApiModelProperty(value = "${field.comment}")
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

}
