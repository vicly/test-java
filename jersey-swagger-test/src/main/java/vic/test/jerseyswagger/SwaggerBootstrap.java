package vic.test.jerseyswagger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Init swagger via HttpServlet, another way is done in jaxrs Application,
 * see {@link JerseySwaggerApplication}
 *
 * @author Vic Liu
 */
public class SwaggerBootstrap extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        SwaggerUtil.config();;

    }
}
