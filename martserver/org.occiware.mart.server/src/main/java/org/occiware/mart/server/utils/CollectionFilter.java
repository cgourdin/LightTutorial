/**
 * Copyright (c) 2015-2017 Inria
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Contributors:
 * - Christophe Gourdin <christophe.gourdin@inria.fr>
 */
package org.occiware.mart.server.utils;

/**
 * This object represents a filter for collections listings. This is used with
 * ConfigurationManager Object.
 *
 * @author Christophe Gourdin
 */
public class CollectionFilter {

    public static final int OPERATOR_EQUAL = 0;
    public static final int OPERATOR_LIKE = 1;
    /**
     * Operator : 0: Equal, 1: LIKE.
     */
    private int operator = 0;

    /**
     * If the filter apply on a category.
     */
    private String categoryFilter = "";
    /**
     * If the filter apply on a sub category (like mixin tag collection filter with a compute in sub category.
     */
    private String subCategoryFilter = "";

    /**
     * Attribute on which apply the constraint (or empty string for any
     * attributes).
     */
    private String attributeFilter = "";

    /**
     * For the case we filter on a single relative path.
     */
    private String filterOnEntitiesPath = "";

    /**
     * Constraint value from attribute values.
     */
    private String value = null;
    private int numberOfItemsPerPage = Constants.DEFAULT_NUMBER_ITEMS_PER_PAGE;
    private int currentPage = Constants.DEFAULT_CURRENT_PAGE;

    /**
     * Build a collectionFilter object with default values, operation : Equal
     * attributeFilter : empty (all attributes). value : null => all values.
     */
    public CollectionFilter() {

    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public String getAttributeFilter() {
        return attributeFilter;
    }

    public void setAttributeFilter(String attributeFilter) {
        this.attributeFilter = attributeFilter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategoryFilter() {
        return categoryFilter;
    }

    public void setCategoryFilter(String categoryFilter) {
        this.categoryFilter = categoryFilter;
    }

    public Integer getValueInt() throws NumberFormatException {
        return Integer.valueOf(value);
    }

    public Float getValueFloat() throws NumberFormatException {
        return Float.valueOf(value);
    }

    public String getFilterOnEntitiesPath() {
        return filterOnEntitiesPath;
    }

    public void setFilterOnEntitiesPath(String filterOnEntitiesPath) {
        this.filterOnEntitiesPath = filterOnEntitiesPath;
    }

    public int getNumberOfItemsPerPage() {
        return this.numberOfItemsPerPage;
    }

    public void setNumberOfItemsPerPage(int numberOfItemsPerPage) {
        this.numberOfItemsPerPage = numberOfItemsPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getSubCategoryFilter() {
        return subCategoryFilter;
    }

    public void setSubCategoryFilter(String subCategoryFilter) {
        this.subCategoryFilter = subCategoryFilter;
    }
}
