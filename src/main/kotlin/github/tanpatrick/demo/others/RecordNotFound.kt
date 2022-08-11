package github.tanpatrick.demo.others

class RecordNotFound(id: Long) : java.lang.RuntimeException("Record with $id does not exist!")