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

package org.occiware.mart.server.parser;

import org.occiware.mart.server.utils.Constants;

import java.util.*;

/**
 * Input datas from queries, this container object may has kind, mixins etc.
 *
 * @author Christophe Gourdin
 */
public class OCCIRequestData {

    private Map<String, Object> attrs = new LinkedHashMap<>();
    /**
     * Scheme # + term.
     */
    private String kind = null;
    /**
     * Scheme # + term list. for resources and links.
     */
    private List<String> mixins = new LinkedList<>();

    /**
     * Action scheme + term.
     */
    private String action = null;
    /**
     * Entity uuid if any.
     */
    private String entityUUID = null;
    /**
     * Entity summary if any.
     */
    private String entitySummary = null;
    /**
     * Entity title if any.
     */
    private String entityTitle = null;


    // Mixin tag scheme+term.
    private String mixinTag = null;
    private String mixinTagTitle = null;
    private List<String> xocciLocations = null;

    /**
     * Used for text/occi and maybe other protocols.
     */
    private HeaderPojo header;

    /**
     * Location of a resource/link or a mixin tag.
     */
    private String location = null;

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs) {
        if (attrs == null) {
            attrs = new LinkedHashMap<>();
        }
        this.attrs = attrs;
    }

    /**
     * Utility method to convert a Map (String, Object) to Map(String, String).
     *
     * @return
     */
    public Map<String, String> getAttrsValStr() {
        if (attrs == null) {
            attrs = new LinkedHashMap<>();
        }
        Map<String, String> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : attrs.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String valStr = value.toString();
            result.put(key, valStr);
        }
        return result;
    }


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getMixins() {
        return mixins;
    }

    public void setMixins(List<String> mixins) {
        if (mixins == null) {
            mixins = new ArrayList<>();
        }
        this.mixins = mixins;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Get entity UUID without format urn:uuid:xxxx-xxxx-xxxx-xxxx
     *
     * @return
     */
    public String getEntityUUID() {
        if (this.entityUUID == null && !getAttrs().isEmpty()) {
            Object entityId = attrs.get(Constants.OCCI_CORE_ID);
            if (entityId != null) {
                entityUUID = attrs.get(Constants.OCCI_CORE_ID).toString();
            }
        }

        if (entityUUID != null && entityUUID.startsWith(Constants.URN_UUID_PREFIX)) {
            entityUUID = entityUUID.replace(Constants.URN_UUID_PREFIX, "");
        }
        return entityUUID;
    }

    public void setEntityUUID(String entityUUID) {
        this.entityUUID = entityUUID;
    }

    public String getMixinTag() {
        return mixinTag;
    }

    public void setMixinTag(String mixinTag) {
        this.mixinTag = mixinTag;
    }

    public String getMixinTagTitle() {
        return mixinTagTitle;
    }

    public void setMixinTagTitle(String mixinTagTitle) {
        this.mixinTagTitle = mixinTagTitle;
    }

    public List<String> getXocciLocations() {
        if (xocciLocations == null) {
            xocciLocations = new ArrayList<>();
        }
        return xocciLocations;
    }

    public void setXocciLocations(List<String> xocciLocations) {
        this.xocciLocations = xocciLocations;
    }

    public void addXocciLocation(final String xOcciLocation) {
        if (xocciLocations == null) {
            xocciLocations = new ArrayList<>();
        }
        if (xOcciLocation == null) {
            return;
        }
        xocciLocations.add(xOcciLocation);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public HeaderPojo getHeader() {
        return header;
    }

    public void setHeader(HeaderPojo header) {
        this.header = header;
    }

    public String getEntitySummary() {
        return entitySummary;
    }

    public void setEntitySummary(String entitySummary) {
        this.entitySummary = entitySummary;
    }

    public String getEntityTitle() {
        return entityTitle;
    }

    public void setEntityTitle(String entityTitle) {
        this.entityTitle = entityTitle;
    }

    public void printDataToOutput() {

        System.out.println("Is this data has attributes : " + !this.attrs.isEmpty());

        // Print values.
        System.out.println("**************************");
        System.out.println("Entity id: " + this.getEntityUUID());
        System.out.println("Kind : " + this.getKind());
        System.out.println("Location: " + this.getLocation());
        System.out.println("Mixin tag: " + this.getMixinTag());
        System.out.println("Mixin tag title: " + this.getMixinTagTitle());
        List<String> xlocations = this.getXocciLocations();
        for (String xlocation : xlocations) {
            System.out.println("x location: " + xlocation);
        }

        if (this.getMixins() != null && !this.getMixins().isEmpty()) {
            for (String mixinId : mixins) {
                System.out.println("Mixin : " + mixinId);
            }
        }
        if (this.getAttrs() != null && !this.getAttrs().isEmpty()) {
            for (Map.Entry<String, Object> entry : attrs.entrySet()) {

                Object valueObj = entry.getValue();
                System.out.println("Attribute : " + entry.getKey() + " --> " + valueObj.toString());
            }
        }
    }

    /**
     * Check if this container has datas.
     *
     * @return
     */
    public boolean hasDatas() {
        boolean result = false;
        if (this.getMixinTag() != null
                || this.getKind() != null
                || this.getAction() != null
                || this.getEntityTitle() != null
                || this.getEntitySummary() != null
                || this.getEntityUUID() != null
                || !this.getMixins().isEmpty()
                || !this.getAttrs().isEmpty()
                || this.getMixinTagTitle() != null) {
            result = true;
        }
        return result;
    }
}
