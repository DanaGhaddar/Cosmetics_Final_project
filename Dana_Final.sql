-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 05, 2022 at 02:42 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Dana_Final`
--

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `name` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `price` varchar(255) NOT NULL,
  `origin` varchar(255) NOT NULL,
  `description` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`name`, `category`, `price`, `origin`, `description`) VALUES
('super stay', 'lipsticks', '20$', 'MAYBELLINE', 'Long wear, matte lipstick'),
('Color sensational', 'lipsticks', '15$', 'MAYBELLINE', 'More intense color with an extreme matte finish'),
('Baby lips', 'lipsticks', '25$', 'MAYBELLINE', 'Medicated Lip Balm hydrates lips for 12 hrs'),
('Blossoming Beauty', 'lipsticks', '10$', 'KIKO Milano', 'Moisturizing 3 in 1 stick for lips'),
('Green me', 'lipsticks', '12$', 'KIKO Milano', 'Extreme comfort matte lipsticks'),
('Happy B-Day', 'lipsticks', '26$', 'KIKO Milano', 'Comfortable matte finish lipstick'),
('Revitalift', 'creams', '35$', 'Loreal Paris', '1.5% pure Hyaluronic Acid serum'),
('Age Perfect Skin', 'creams', '35$', 'Loreal Paris', 'Cell renewal antiaging Midnight Serum'),
('Pure Clay', 'creams', '22$', 'Loreal Paris', 'Detox and brighten face mask'),
('Wrinkle expert', 'creams', '22$', 'Loreal Paris', '55+ Age Defense Lotion SPF'),
('Collagen Filler', 'creams', '21$', 'Loreal Paris', 'Collagen Moisturizer Filler'),
('Dr Feel Good', 'powders', '50$', 'Benefit Cosmetics', 'Translucent Powder'),
('Fit me', 'powders', '35$', 'MAYBELLINE', 'Skin stays poreless for 16 hrs'),
('Infiliable fresh wear', 'powders', '44$', 'Benefit Cosmetics', 'Foundation in a powder'),
('Day Fable', 'powders', '63$', 'KIKO Milano', 'Hydra infusion matte powder'),
('ILU Mom', 'powders', '21$', 'Benefit Cosmetics', 'Pressed powder'),
('Refy', 'gels', '24$', 'Sephora', 'Viral brow gel'),
('Rose ink', 'gels', '15$', 'Sephora', 'Tinted gel that conditions the brows'),
('Boy Brow', 'gels', '44$', 'Glossier', 'Fluffy instantly groomed brows'),
('Nyx', 'gels', '28$', 'KIKO Milano', 'Tinted brown mascara'),
('Kosas', 'gels', '11$', 'Benefit Cosmetics', 'Strengthens the brows');

-- --------------------------------------------------------

--
-- Table structure for table `products_purchases`
--

CREATE TABLE `products_purchases` (
  `username` varchar(255) NOT NULL,
  `productname` varchar(255) NOT NULL,
  `quantity` varchar(255) NOT NULL,
  `price` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `name` varchar(255) NOT NULL,
  `age` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`name`, `age`, `location`, `password`) VALUES
('bo', '21', 'joiu', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b'),
('carla jory', '34', 'Hamra', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('dana', '21', 'Beirut', '4fc82b26aecb47d2868c4efbe3581732a3e7cbcc6c2efb32062c08170a05eeb8'),
('dana2', '22', 'Baalbak', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b'),
('danag', '21', 'Beirutt', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('danaghadar', '21', 'Beirut', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('jad', '22', 'switzerland', '6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918'),
('jadd', '23', 'hon', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b'),
('jado', '122', 'hoarding', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD UNIQUE KEY `name` (`name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
