package net.kaushik.example.angularrestspringsecurity.rest.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import net.kaushik.example.angularrestspringsecurity.JsonViews;
import net.kaushik.example.angularrestspringsecurity.dao.blogpost.BlogPostDao;
import net.kaushik.example.angularrestspringsecurity.entity.BlogPost;
import net.kaushik.example.angularrestspringsecurity.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * @author Kaushik Parmar
 */
@Component
@Path("/blogposts")
public class BlogPostResource
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogPostDao blogPostDao;

    @Autowired
    private ObjectMapper mapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String list() throws IOException
    {
        this.logger.info("list()");

        ObjectWriter viewWriter;
        if (this.isAdmin()) {
            viewWriter = this.mapper.writerWithView(JsonViews.Admin.class);
        } else {
            viewWriter = this.mapper.writerWithView(JsonViews.User.class);
        }
        List<BlogPost> allEntries = this.blogPostDao.findAll();

        return viewWriter.writeValueAsString(allEntries);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public BlogPost read(@PathParam("id") Long id)
    {
        this.logger.info("read(id)");

        BlogPost blogPost = this.blogPostDao.find(id);
        if (blogPost == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return blogPost;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BlogPost create(BlogPost blogPost)
    {
        this.logger.info("create(): " + blogPost);

        return this.blogPostDao.save(blogPost);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public BlogPost update(@PathParam("id") Long id, BlogPost blogPost)
    {
        this.logger.info("update(): " + blogPost);

        return this.blogPostDao.save(blogPost);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public void delete(@PathParam("id") Long id)
    {
        this.logger.info("delete(id)");

        this.blogPostDao.delete(id);
    }

    private boolean isAdmin()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (!(principal instanceof UserDetails)) {
            return false;
        }

        UserDetails userDetails = (UserDetails) principal;

        return userDetails.getAuthorities().contains(Role.ADMIN);
    }
}
