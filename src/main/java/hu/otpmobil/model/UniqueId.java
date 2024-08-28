package hu.otpmobil.model;

import java.util.Objects;

public class UniqueId {

    private String webShopId;
    private String customerId;

    public String getWebShopId() {
        return webShopId;
    }

    public UniqueId webShopId(String webShopId) {
        this.webShopId = webShopId;
        return this;
    }

    public UniqueId customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UniqueId uniqueId = (UniqueId) object;
        return Objects.equals(webShopId, uniqueId.webShopId)
                && Objects.equals(customerId, uniqueId.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(webShopId, customerId);
    }

    @Override
    public String toString() {
        return "UniqueId{" +
                "webShopId='" + webShopId + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }

}
