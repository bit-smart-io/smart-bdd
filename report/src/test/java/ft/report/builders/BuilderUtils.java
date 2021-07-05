package ft.report.builders;

import io.bitsmart.bdd.report.utils.Builder;

import java.util.List;
import java.util.stream.Collectors;

public class BuilderUtils {

    /**
     * Builder class
     * public ParentItem build() {
     * return new ParentItem(build(items), childString);
     * }
     * <p>
     * private List<ItemBuilder> testCases; build(items)
     * Pros - only need to implement build()
     * Cons - you might end up with complex build(Map<String, List<Integer>)
     * private ItemListBuilder items; items.build()
     * Pros - can handle any datatype
     * Cons - have to create extra collection builders
     * <p>
     *
     * Collections
     * Also consider
     * public ParentItem withItems(List<ItemBuilder> itemBuilders) {
     * this.itemBuilders.clear();
     * this.itemBuilders.addAll(itemBuilders);
     * return this;
     * }
     * <p>
     * public ParentItem addItem(ItemBuilder itemBuilder) {
     * this.itemBuilders.add(itemBuilder);
     * return this;
     * }
     */
    public static <T, B extends Builder<T>> List<T> build(List<B> builders) {
        if (builders == null) {
            return null;
        }
        return builders.stream().map(Builder::build).collect(Collectors.toList());
    }
}
