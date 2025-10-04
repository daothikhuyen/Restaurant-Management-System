-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 04, 2025 at 08:43 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rms`
--

-- --------------------------------------------------------

--
-- Table structure for table `billing`
--

CREATE TABLE `billing` (
  `id` bigint NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `notes` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `status` enum('CANCELLED','PAID','PENDING') COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `table_id` bigint DEFAULT NULL,
  `tax` double DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`id`, `created_date`, `customer_id`, `discount`, `notes`, `order_id`, `status`, `table_id`, `tax`, `total_amount`, `updated_date`) VALUES
(1, '2025-10-04 07:22:39.919113', 1, NULL, '', 1, 'PENDING', 8, NULL, 250000, '2025-10-04 09:34:17.043167');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint NOT NULL,
  `icon_url` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `icon_url`, `name`, `is_deleted`) VALUES
(1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Flat_tick_icon.svg/768px-Flat_tick_icon.svg.png', 'Main course', b'0'),
(2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Flat_tick_icon.svg/768px-Flat_tick_icon.svg.png', 'Side dishes', b'0'),
(3, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Flat_tick_icon.svg/768px-Flat_tick_icon.svg.png', 'Dessert', b'0'),
(4, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Flat_tick_icon.svg/768px-Flat_tick_icon.svg.png', 'Drinking water', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` bigint NOT NULL,
  `address` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `gender` char(1) COLLATE utf8mb3_unicode_ci NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `last_name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `middle_name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `address`, `created_by`, `created_date`, `dob`, `email`, `first_name`, `gender`, `is_deleted`, `last_name`, `middle_name`, `phone`, `updated_by`, `updated_date`) VALUES
(1, '123 Đường Lê Lợi, Quận 1, TP.HCM', NULL, '2025-10-03 21:02:48.028841', NULL, 'nguyenvana@example.com', 'Nguyen', 'M', b'1', 'A', 'Van', '0987654321', NULL, '2025-10-03 21:02:48.028841'),
(2, '385 Đường Lê Lợi, Quận 1, TP.HCM', 'admin', '2025-10-03 21:03:59.313537', NULL, 'nguyenhalan@example.com', 'Nguyen', 'F', b'0', 'Lan', 'Ha', '0987654321', 'admin', '2025-10-03 21:03:59.313537'),
(3, '385 Đường Lê Lợi, Quận 1, TP.HCM', 'admin', NULL, NULL, 'nguyenhalan@example.com', 'Nguyen', 'F', b'0', 'Lan', 'Ha', '0987654322', 'admin', '2025-10-03 21:13:29.284006');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint NOT NULL,
  `created_by` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `order_type` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `payment_id` bigint DEFAULT NULL,
  `table_id` bigint DEFAULT NULL,
  `total_amount` double NOT NULL,
  `updated_by` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `created_by`, `created_date`, `is_deleted`, `order_type`, `payment_id`, `table_id`, `total_amount`, `updated_by`, `updated_date`, `user_id`) VALUES
(1, 'Đào Thị Khuyên', '2025-10-04 07:22:39.805053', b'0', 'Online', 2, 8, 250000, 'Đào Thị Khuyên', '2025-10-04 07:22:39.805053', 1);

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` bigint NOT NULL,
  `order_id` bigint DEFAULT NULL,
  `price` double NOT NULL,
  `product_id` bigint DEFAULT NULL,
  `quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`id`, `order_id`, `price`, `product_id`, `quantity`) VALUES
(1, 1, 50000, 1, 2),
(2, 1, 150000, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` bigint NOT NULL,
  `created_by` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `is_delete` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `status` tinyint NOT NULL,
  `updated_by` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL
) ;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `created_by`, `created_date`, `is_delete`, `name`, `status`, `updated_by`, `updated_date`) VALUES
(1, 'Đào Thị Khuyên', '2025-10-01 22:06:07.991473', b'0', 'Cash', 0, 'Đào Thị Khuyên', '2025-10-01 22:06:07.991473'),
(2, 'Đào Thị Khuyên', '2025-10-01 22:40:56.671304', b'0', 'Bank', 0, 'Đào Thị Khuyên', '2025-10-01 22:40:56.671410');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `description` text COLLATE utf8mb3_unicode_ci,
  `image_url` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `price` double NOT NULL,
  `price_promotion` double NOT NULL,
  `updated_by` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `category_id`, `created_by`, `created_date`, `description`, `image_url`, `is_deleted`, `name`, `price`, `price_promotion`, `updated_by`, `updated_date`) VALUES
(1, 1, 'Đào Thị Khuyên', '2025-10-02 12:09:17.153209', 'Beefsteak is a dish originating from the West that is loved by many people. This is not only a dish but also a work of culinary art with the combination of tender beef with vegetables, fruits, and delicious sauce. So is it difficult to make beefsteak? Let\'s find out with us!', 'http://res.cloudinary.com/dfa17qrac/image/upload/v1755243539/images/mgppcv00ezxvztuspqaj.jpg', b'0', 'Steak', 480, 230, 'Đào Thị Khuyên', '2025-10-02 12:09:17.153209'),
(2, 1, 'Đào Thị Khuyên', '2025-10-02 12:09:48.115808', 'Beefsteak is a dish originating from the West that is loved by many people. This is not only a dish but also a work of culinary art with the combination of tender beef with vegetables, fruits, and delicious sauce. So is it difficult to make beefsteak? Let\'s find out with us!', 'http://res.cloudinary.com/dfa17qrac/image/upload/v1755243539/images/mgppcv00ezxvztuspqaj.jpg', b'0', 'Steamed Shrimp', 580, 530, 'Đào Thị Khuyên', '2025-10-02 12:09:48.115808'),
(3, 1, 'Đào Thị Khuyên', '2025-10-02 12:10:02.050340', 'Beefsteak is a dish originating from the West that is loved by many people. This is not only a dish but also a work of culinary art with the combination of tender beef with vegetables, fruits, and delicious sauce. So is it difficult to make beefsteak? Let\'s find out with us!', 'http://res.cloudinary.com/dfa17qrac/image/upload/v1755243539/images/mgppcv00ezxvztuspqaj.jpg', b'0', 'Fried chicken', 580, 530, 'Đào Thị Khuyên', '2025-10-02 12:10:02.050340'),
(4, 2, 'Đào Thị Khuyên', '2025-10-02 12:10:16.428820', 'Beefsteak is a dish originating from the West that is loved by many people. This is not only a dish but also a work of culinary art with the combination of tender beef with vegetables, fruits, and delicious sauce. So is it difficult to make beefsteak? Let\'s find out with us!', 'http://res.cloudinary.com/dfa17qrac/image/upload/v1755243539/images/mgppcv00ezxvztuspqaj.jpg', b'0', 'Meat salad', 580, 530, 'Đào Thị Khuyên', '2025-10-02 12:10:16.428820'),
(5, 4, 'Đào Thị Khuyên', '2025-10-02 12:10:39.116175', 'Beefsteak is a dish originating from the West that is loved by many people. This is not only a dish but also a work of culinary art with the combination of tender beef with vegetables, fruits, and delicious sauce. So is it difficult to make beefsteak? Let\'s find out with us!', 'http://res.cloudinary.com/dfa17qrac/image/upload/v1755243539/images/mgppcv00ezxvztuspqaj.jpg', b'0', 'Coconut water', 580, 530, 'Đào Thị Khuyên', '2025-10-02 12:10:39.116175');

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_table`
--

CREATE TABLE `restaurant_table` (
  `id` bigint NOT NULL,
  `area` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `capacity` int NOT NULL,
  `created_by` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `status` tinyint NOT NULL,
  `updated_by` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL
) ;

--
-- Dumping data for table `restaurant_table`
--

INSERT INTO `restaurant_table` (`id`, `area`, `capacity`, `created_by`, `created_date`, `is_deleted`, `name`, `status`, `updated_by`, `updated_date`) VALUES
(1, '64-Quận 1, HCM', 4, 'Đào Thị Khuyên', '2025-10-01 05:38:16.166526', b'0', 'Bàn số 01', 0, NULL, '2025-10-01 05:38:16.166526'),
(2, '64-Quận 1, HCM', 4, 'Đào Thị Khuyên', '2025-10-01 05:39:09.122589', b'0', 'Bàn số 02', 0, 'Đào Thị Khuyên', '2025-10-01 05:39:09.122589'),
(3, '64-Quận 1, HCM', 4, 'Đào Thị Khuyên', '2025-10-01 05:39:13.835661', b'0', 'Bàn số 03', 0, 'Đào Thị Khuyên', '2025-10-01 05:39:13.835661'),
(4, '64-Quận 1, HCM', 6, 'Đào Thị Khuyên', '2025-10-01 05:39:19.008826', b'0', 'Bàn số 04', 0, 'Đào Thị Khuyên', '2025-10-01 05:39:19.008826'),
(5, '64-Quận 1, HCM', 6, 'Đào Thị Khuyên', '2025-10-01 05:39:23.129855', b'0', 'Bàn số 05', 0, 'Đào Thị Khuyên', '2025-10-01 05:39:23.129855'),
(6, '64-Quận 1, HCM', 6, 'Đào Thị Khuyên', '2025-10-01 05:39:27.508830', b'0', 'Bàn số 06', 0, 'Đào Thị Khuyên', '2025-10-01 05:39:27.508830'),
(7, '64-Quận 1, HCM', 8, 'Đào Thị Khuyên', '2025-10-01 05:39:32.848934', b'0', 'Bàn số 07', 0, 'Đào Thị Khuyên', '2025-10-01 05:39:32.848934');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `billing`
--
ALTER TABLE `billing`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `restaurant_table`
--
ALTER TABLE `restaurant_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `billing`
--
ALTER TABLE `billing`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `restaurant_table`
--
ALTER TABLE `restaurant_table`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
