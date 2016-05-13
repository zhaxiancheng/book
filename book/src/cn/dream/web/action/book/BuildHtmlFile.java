package cn.dream.web.action.book;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import cn.dream.bean.book.BookInfo;

public class BuildHtmlFile {
	
	public static void createProductHtml(BookInfo book, File saveDir){
		try {
			if(!saveDir.exists()) saveDir.mkdirs();
			VelocityContext context = new VelocityContext();
			context.put("book", book);
			Template template = Velocity.getTemplate("book/bookview.html");
			FileOutputStream outStream = new FileOutputStream(new File(saveDir, book.getBookid()+".shtml"));
			OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
			BufferedWriter sw = new BufferedWriter(writer);
			template.merge(context, sw);
			sw.flush();
			sw.close();
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
