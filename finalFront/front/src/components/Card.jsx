import PropTypes from 'prop-types';

export default function Card({ image, title, author, description,price, onAddToCart  }) {
    return (
      <div className="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow">
        {/* Image */}
        <img
          src={image}
          alt={title}
          className="w-full h-48 object-cover"
        />
  
        {/* Content */}
        <div className="p-4">
          <h3 className="text-xl font-bold text-gray-800">{title}</h3>
          <p className="mt-1 text-gray-600">{author}</p>
          <p className="mt-2 text-gray-700">{description}</p>
          <p className="mt-3 text-gray-700">Price: ${price}</p>
  
          {/* Add to Cart Button */}
          <button
            onClick={onAddToCart}
            className="w-full mt-4 bg-blue-500 text-white py-2 rounded-md hover:bg-blue-600 transition duration-300"
          >
            Add to Cart
          </button>
        </div>
      </div>
    );
}

Card.propTypes = {
  image: PropTypes.string,
  title: PropTypes.string.isRequired,
  author: PropTypes.string,
  description: PropTypes.string.isRequired,
  price: PropTypes.number.isRequired,
  onAddToCart: PropTypes.func.isRequired,
};