//Enum-Klasse Priority
enum class Priority {
    HIGH,
    MEDIUM,
    LOW;

//Companion Object, kann ohne zusätzliche Instanziierung mit der zugeordneten Klasse (Priority) aufgerufen werden
companion object {
    fun fromFactor ( factor : Double ): Priority {
        return when ( factor . toInt ()) {  //Konvertiert den Double-Wert in einen Int-Wert und schneidet die Nachkommastelle ab
            1 -> HIGH
            2 -> MEDIUM
            3 -> LOW
            else -> throw IllegalArgumentException ("Ungültiger Faktor : $factor ")
        }
    }
}
}
