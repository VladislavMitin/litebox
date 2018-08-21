package ru.mitin.vladislav.litebox;

import android.content.Context;

import org.junit.Test;
import static org.junit.Assert.*;

import ru.mitin.vladislav.litebox.interfaces.IResultRepository;
import ru.mitin.vladislav.litebox.presenters.GoogleSearchPresenter;

import static org.mockito.Mockito.mock;

/*тесты в целом писал, под rxjava еще нет, разбираться надо, поэтому вот один нерабочий тест
но суть такая
 */
public class PresenterTest {
    @Test
    public void searchSuccess() {
        Context context = mock(Context.class);

        View view = new View();
        Repository repository = new Repository();
        repository.setEmptyResult(false);

        GoogleSearchPresenter presenter = new GoogleSearchPresenter(context, view, repository);
        presenter.search("test", false);

        assertNotEquals(view.getResults().size(), 0);
        assertEquals(view.isShowProgressBar(), true);
        assertEquals(view.isHideProgressBar(), true);
    }
}
