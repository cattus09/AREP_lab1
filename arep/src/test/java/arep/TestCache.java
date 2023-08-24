package arep;

import org.junit.Test;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class TestCache extends TestCase{
    @Test
    public void testIsOnCache_ExistingTitle() {
        Cache cache = Cache.getInstance();
        cache.clear();

        String title = "Sample Title";
        String description = "Sample Description";

        Cache.cache.put(title, description);

        assertTrue(cache.isOnCache(title));
    }

    @Test
    public void testIsOnCache_NonExistingTitle() {
        Cache cache = Cache.getInstance();
        cache.clear();

        assertFalse(cache.isOnCache("Non-Existing Title"));
    }

    @Test
    public void testGetMovieDescription_ExistingTitle() {
        Cache cache = Cache.getInstance();
        cache.clear();

        String title = "Sample Title";
        String description = "Sample Description";

        Cache.cache.put(title, description);

        assertEquals(description, cache.getMovieDescription(title));
    }

    @Test
    public void testGetMovieDescription_NonExistingTitle() {
        Cache cache = Cache.getInstance();
        cache.clear();

        assertNull(cache.getMovieDescription("Non-Existing Title"));
    }

    @Test
    public void testGetInstance_Singleton() {
        Cache cache1 = Cache.getInstance();
        Cache cache2 = Cache.getInstance();

        assertSame(cache1, cache2);
    }

    @Test
    public void testClear() {
        Cache cache = Cache.getInstance();
        Cache.cache.put("Sample Title", "Sample Description");

        cache.clear();

        assertTrue(Cache.cache.isEmpty());
    }
}



