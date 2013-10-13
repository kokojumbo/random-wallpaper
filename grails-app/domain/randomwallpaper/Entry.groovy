package randomwallpaper

class Entry {

    static constraints = {
		originalUrl nullable: true, bindable: false
		filePath nullable: true, bindable: false
		fileName nullable: true, bindable: false
		mime nullable:true, bindable: false
		dateCreated()
		}
	String originalUrl
	String filePath
	String fileName
	String mime
	Date dateCreated
	
	}
