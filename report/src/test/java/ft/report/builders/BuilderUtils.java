package ft.report.builders;

import io.bitsmart.bdd.report.utils.Builder;

import java.util.List;
import java.util.stream.Collectors;

public class BuilderUtils {

    /**
     * Builder class
     *  public ParentItem build() {
     *         return new TestSuite(build(items), childString);
     *     }

     *  private List<ItemBuilder> testCases; build(items)
     *   Pros - only need to implement build()
     *   Cons - you might end up with complex build(Map<String, List<Integer>)
     *  private ItemListBuilder items; items.build()
     *   Pros - can handle any datatype
     *   Cons - have to create extra collection builders
     */
    public static <T, B extends Builder<T>> List<T> build(List<B> builders) {
        return builders.stream().map(Builder::build).collect(Collectors.toList());
    }
}
