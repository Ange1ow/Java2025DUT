module store.model {
    // Ми "транзитивно" вимагаємо lombok, щоб модулі,
    // які використовують store.model, також мали доступ до lombok
    requires transitive lombok;

    // Ми експортуємо наші моделі, щоб інші модулі могли їх бачити
    exports com.store.model.product;
    exports com.store.model.order;
}