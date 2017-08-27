package net.kaushik.example.angularrestspringsecurity.dao.blogpost;

import net.kaushik.example.angularrestspringsecurity.dao.Dao;
import net.kaushik.example.angularrestspringsecurity.entity.BlogPost;

/**
 * Definition of a Data Access Object that can perform CRUD Operations for {@link BlogPost}s.
 *
 * @author Kaushik Parmar
 */
public interface BlogPostDao extends Dao<BlogPost, Long>
{
}
