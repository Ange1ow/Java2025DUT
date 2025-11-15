module store.processing {
    // Вимагає моделі та сховище
    requires transitive store.model;
    requires store.storage;

    // Експортує лише головний сервіс
    exports com.store.processing;
    // Клас OrderProcessorTask залишається внутрішнім (private) для модуля
}