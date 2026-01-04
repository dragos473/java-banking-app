## Java Banking System – Core Engine
A robust, Java-based e-banking engine designed to handle high-volume account operations, multi-currency transactions, and automated reporting. This project focuses on **Object-Oriented Programming (OOP)** and **Data Integrity** within a financial context.

**🚀 Key Features**

**Account Lifecycle Management:** Supports multiple account types (Classic, Savings) with automated IBAN generation and balance tracking.

**Card Security & Logic:** Implements standard and "One-Time-Pay" virtual cards that auto-regenerate upon use to prevent fraud.

**Financial Operations:** Handles deposits, multi-currency transfers, and online payments with real-time currency conversion.

**Resource Protection:** Includes a "Minimum Balance" safety mechanism that automatically freezes cards to prevent account overdraws.

**Analytical Reporting:** Generates comprehensive transaction and spending reports in JSON format for financial auditing.

**🛠 Technical Stack**

**Language:** Java 21

**Data Processing:** JSON(Jackson) for structured I/O.

**Architecture:** Modular OOP design with a focus on inheritance and encapsulation.

This repository contains the core transaction engine. For the enterprise-grade refactor involving design patterns and business account logic, see the [java-banking-app-improved](https://github.com/dragos473/java-banking-app-improved).
