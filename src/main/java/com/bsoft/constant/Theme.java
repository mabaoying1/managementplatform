package com.bsoft.constant;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

public class Theme implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static final Logger logger;
    private Arg arg;
    private Inds inds;
    private String theme_key;
    private Dim dim;
    private ShowColumn slumn;
    private Option option;
    private Param param;
    private ExpenseDim exp;
    private String indid;
    private List<Map<String, Object>> argList;
    private Map<String, Object> argMap;
    private Map<String, String> slumnDim;
    private Map<String, Map<String, Object>> indmap;
    private List<Map<String, Object>> indItemsList;
    private List<String> slumnList;
    private Map<String, Object> optionMap;
    private Parameter parameterView;
    private Map<String, String> col2Filed;
    private Map<String, String> filed2Col;
    private Map<String, String> indKey2ID;
    private Map<String, String> indID2Key;
    private List<String> indIdList;

    static {
        logger = LoggerFactory.getLogger((Class)Theme.class);
    }

    @XmlTransient
    public ExpenseDim getDimExpenseMap() {
        return this.exp;
    }

    public void setDimExpenseMap(final ExpenseDim exp) {
        this.exp = exp;
    }

    @XmlTransient
    public Map<String, String> getIndID2Key() {
        return this.indID2Key;
    }

    public void setIndID2Key(final Map<String, String> indID2Key) {
        this.indID2Key = indID2Key;
    }

    @XmlTransient
    public Map<String, String> getIndKey2ID() {
        return this.indKey2ID;
    }

    public void setIndKey2ID(final Map<String, String> indKey2ID) {
        this.indKey2ID = indKey2ID;
    }

    @XmlElement(name = "indid")
    public String getIndid() {
        if (StringUtils.isNotBlank((CharSequence)this.indid) && this.inds != null && this.inds.getInds().size() > 0) {
            this.indid = this.inds.getInds().get(0).getIndId();
        }
        return this.indid;
    }

    public void setIndid(final String indid) {
        this.indid = indid;
    }

    public Theme() {
        this.dim = new Dim();
        this.slumn = new ShowColumn();
        this.option = new Option();
        this.param = new Param();
        this.exp = new ExpenseDim();
        this.argList = Lists.newArrayList();
        this.indKey2ID = Maps.newHashMap();
        this.indID2Key = Maps.newHashMap();
    }

    @XmlTransient
    public Map getOptionMap() {
        if (this.optionMap == null && this.option != null) {
            this.optionMap = (Map<String, Object>)this.option.toMap();
        }
        return this.optionMap;
    }

    public void setArgs(final Arg arg) {
        this.arg = arg;
    }

    @XmlTransient
    public Map<String, String> getCol2Filed() {
        return this.col2Filed;
    }

    public void setCol2Filed(final Map<String, String> col2Filed) {
        this.col2Filed = col2Filed;
    }

    @XmlTransient
    public Map<String, String> getFiled2Col() {
        return this.filed2Col;
    }

    public void setFiled2Col(final Map<String, String> filed2Col) {
        this.filed2Col = filed2Col;
    }

    public void setArgList(final List<Map<String, Object>> argList) {
        this.argList = argList;
    }

    @XmlElement(name = "option")
    public Option getOption() {
        return this.option;
    }

    public void setOption(final Option option) {
        this.option = option;
    }

    @XmlElement(name = "inds")
    public Inds getInds() {
        return this.inds;
    }

    public void setInds(final Inds inds) {
        this.inds = inds;
    }

    @XmlAttribute(name = "theme_key")
    public String getTheme_key() {
        return this.theme_key;
    }

    public void setTheme_key(final String theme_key) {
        this.theme_key = theme_key;
    }

    @XmlElement(name = "dim")
    public Dim getDim() {
        return this.dim;
    }

    public void setDim(final Dim dim) {
        this.dim = dim;
    }

    @XmlElement(name = "slumn")
    public ShowColumn getSlumn() {
        return this.slumn;
    }

    public void setSlumn(final ShowColumn slumn) {
        this.slumn = slumn;
    }

    @XmlElement(name = "param")
    public Param getParam() {
        return this.param;
    }

    public void setParam(final Param param) {
        this.param = param;
    }

    public List<String> toSlumnList() {
        if (this.slumn == null) {
            Theme.logger.error("\u9700\u8981\u5c55\u793a\u7684\u7ef4\u5ea6\u8fd8\u672a\u914d\u7f6e\uff01");
            return null;
        }
        if (this.slumnList == null) {
            this.slumnList =Lists.newArrayList();
            for (final Column col : this.slumn.getElumn()) {
                this.slumnList.add(col.getDim());
            }
        }
        return this.slumnList;
    }

    public Map<String, Map<String, Object>> toIndsMap() {
        if (this.inds == null) {
            Theme.logger.error("\u9700\u8981\u5c55\u793a\u7684\u6307\u6807\u8fd8\u672a\u914d\u7f6e\uff01");
            return null;
        }
        if (this.indmap == null) {
            this.indmap = Maps.newHashMap();
            for (final Ind ind : this.inds.getInds()) {
                this.indmap.put(ind.getIndId(), ind.getMap());
            }
        }
        return this.indmap;
    }

    public List<Map<String, Object>> toIndItemsList() {
        if (this.inds == null) {
            Theme.logger.error("\u9700\u8981\u5c55\u793a\u7684\u6307\u6807\u8fd8\u672a\u914d\u7f6e\uff01");
            return null;
        }
        if (this.indItemsList == null) {
            this.indItemsList = Lists.newArrayList();
            for (final Ind ind : this.inds.getInds()) {
                this.indItemsList.add(ind.getMap());
            }
        }
        return this.indItemsList;
    }

    public List<String> toIndIdList() {
        if (this.inds == null) {
            Theme.logger.error("\u9700\u8981\u5c55\u793a\u7684\u6307\u6807\u8fd8\u672a\u914d\u7f6e\uff01");
            return null;
        }
        if (this.indIdList == null) {
            this.indIdList = Lists.newArrayList();
            this.indItemsList = Lists.newArrayList();
            this.indmap = Maps.newHashMap();
            for (final Ind ind : this.inds.getInds()) {
                this.indIdList.add(ind.getIndId());
                this.indKey2ID.put(ind.getIndKey(), ind.getIndId());
                if (StringUtils.isNotBlank((CharSequence)ind.getTitle())) {
                    this.indID2Key.put(ind.getIndId(), ind.getTitle());
                }
                else {
                    this.indID2Key.put(ind.getIndId(), ind.getIndKey());
                }
                this.indItemsList.add(ind.getMap());
                this.indmap.put(ind.getIndId(), ind.getMap());
            }
        }
        return this.indIdList;
    }

    public Map mapping2value(final Dim o, final Map map) {
        final Map result = Maps.newHashMap();
        if (this.filed2Col == null) {
            this.filed2Col =Maps.newHashMap();
            final Field[] declaredFields = o.getClass().getDeclaredFields();
            Field[] array;
            for (int length = (array = declaredFields).length, i = 0; i < length; ++i) {
                final Field field = array[i];
                final int fieldValue = field.getModifiers();
                if (!Modifier.isStatic(fieldValue)) {
                    field.getAnnotatedType();
                    field.setAccessible(true);
                    final String dimFiled = field.getName();
                    String argKey = null;
                    try {
                        argKey = field.get(o).toString().toUpperCase();
                    }
                    catch (IllegalArgumentException | IllegalAccessException ex2) {
                        final Exception ex;
                        final Exception e1 = ex2;
                        Theme.logger.error(e1.getMessage());
                    }
                    this.filed2Col.put(dimFiled, argKey);
                    final Object value = map.get(dimFiled.toUpperCase());
                    if (value != null) {
                        result.put(argKey, value);
                    }
                }
            }
        }
        else {
            for (final String key : this.filed2Col.keySet()) {
                final Object value2 = map.get(key.toUpperCase());
                if (value2 != null) {
                    result.put(this.filed2Col.get(key), value2);
                }
            }
        }
        return result;
    }

    public void setParameter(final Map<String, Object> arg) {
        final Map<String, Object> dMap = Maps.newHashMap();
        for (final Parameter pa : this.param.getParameters()) {
            if (this.parameterView != null && pa.getDim_key().equals(this.parameterView.getDim_key())) {
                if (StringUtils.isBlank((CharSequence)this.parameterView.getValue())) {
                    continue;
                }
                dMap.put(pa.getDim(), this.parameterView.getValue());
                this.parameterView = null;
            }
            else {
                if (StringUtils.isBlank((CharSequence)pa.getValue())) {
                    continue;
                }
                dMap.put(pa.getDim(), pa.getValue());
            }
        }
        dMap.putAll(this.argMap);
        this.argList.add(dMap);
    }

    @XmlTransient
    public List<Map<String, Object>> getArgList() {
        this.argMap = (Map<String, Object>)this.mapping2value(this.dim, ((Args)this.arg).toUpperKeyForSuperFiledsMap());
        this.argList.clear();
        if (this.param.isHaveParameters()) {
            this.setParameter(this.argMap);
        }
        else {
            this.argList.add(this.argMap);
        }
        return this.argList;
    }

    @XmlTransient
    public Map<String, String> getSlumnDim() {
        if (this.slumnDim == null && this.slumn != null) {
            this.slumnDim = Maps.newHashMap();
            for (final Column col : this.slumn.getElumn()) {
                this.slumnDim.put(col.getDim().toUpperCase(), col.getKey().toUpperCase());
            }
        }
        return this.slumnDim;
    }

    public void setSlumnDim(final Map<String, String> slumnDim) {
        this.slumnDim = slumnDim;
    }

    public void putParam(final Parameter pa) {
        this.parameterView = pa;
    }
}
