import org.codehaus.groovy.grails.support.DevelopmentResourceLoader;
import randomwallpaper.Entry

import grails.util.Environment;

class BootStrap {

    def init = { servletContext ->
		environments {
			development {
				
				def dir = new File("web-app/images/pictures/")
				dir.mkdirs()
				
				/*def entry = new Entry()
				entry.mime = 'image/jpeg'
				entry.fileName = "xxx.jpg"
				entry.save()*/
			
			}
		}
		
    }
    def destroy = {
    }
}
