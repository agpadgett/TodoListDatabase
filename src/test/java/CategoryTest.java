import org.junit.*;
import static org.junit.Assert.*;

public class CategoryTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Rule
  public DataBaseRule database = new DataBaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Category.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Category firstCategory = new Category("Household chores", 1);
    Category secondCategory = new Category("Household chores", 1);
    assertTrue(firstCategory.equals(secondCategory));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Category myCategory = new Category("Household chores", 1);
    myCategory.save();
    assertTrue(Category.all().get(0).equals(myCategory));
  }

  @Test
  public void find_findCategoryInDatabase_true() {
    Category myCategory = new Category("Household chores", 1);
    myCategory.save();
    Category savedCategory = Category.find(myCategory.getId());
    assertTrue(myCategory.equals(savedCategory));
  }

  @Test
  public void save_savesCtegoryIDIntDB_true() {
    Category myCategory = new Category("Household chores");
    myCategory.save();
    Task myTask = new Task("Mow the law", myCategory.getId());
    myTask.save();
    Task savedTask = Task.find(myTask.getId());
    assertEquals(savedTask.getCagegoryId(), myCategory.getId());
  }
}
