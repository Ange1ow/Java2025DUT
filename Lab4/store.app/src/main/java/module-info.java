module store.app {
    // Вимагає всі модулі, необхідні для роботи
    requires store.model;
    requires store.storage;
    requires store.processing;

    // Вимагає Faker
    requires javafaker;
    requires java.sql;
}