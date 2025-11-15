module store.storage {
    // Вимагає моделі для зберігання
    requires transitive store.model;

    // Експортує свій сервіс
    exports com.store.storage;
}