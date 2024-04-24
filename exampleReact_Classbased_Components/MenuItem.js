import React, {Component} from 'react';
import './MenuItem.css';

//A Code Example: a generic Class-based React Component
class MenuItem extends Component {

    render() {
      return <div className='nav-menu-item'>
        {this.props.itemLabel}
      </div>
    }
  }

  export default MenuItem;