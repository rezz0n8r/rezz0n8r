import React, {Component} from 'react';
import MenuItem from './MenuItem';
import './LNavMenu.css';

//A Code Example: a generic Class-based React Component
class LNavMenu extends Component {

    render() {
      return <div className='nav-menu'>
        <MenuItem itemLabel="one" />
        <MenuItem itemLabel="two" />
        <MenuItem itemLabel="three" />
      </div>
    }
  }

  export default LNavMenu;