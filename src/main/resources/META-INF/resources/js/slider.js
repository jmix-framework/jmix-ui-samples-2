import 'jquery/jquery.js'
import 'jquery-ui/dist/jquery-ui.js'
import {PolymerElement} from '@polymer/polymer/polymer-element.js';
import {ElementMixin} from '@vaadin/component-base/src/element-mixin.js';

class Slider extends ElementMixin(PolymerElement) {

    static get is() {
        return 'demo-slider';
    }

    static get properties() {
        return {
            min: {
                type: Number,
                value: 0,
                observer: '_onMinChange'
            },

            max: {
                type: Number,
                value: 100,
                observer: '_onMaxChange'
            },

            value: {
                type: Number,
                notify: true,
                observer: '_onValueChange'
            },

            /** @private */
            _slider: {
                type: Object
            }
        }
    }

    ready() {
        super.ready();
        let $jQuery = jQuery.noConflict();
        this._slider = $jQuery(this);
        this._slider.slider({
            min: this.min,
            max: this.max,

            change: function (event, ui) {
                if (this.value === ui.value) {
                    return;
                }
                this.value = ui.value;
                const slideChangeEvent = new CustomEvent(
                    'custom-slide-changed',
                    {detail: {value: ui.value}}
                );
                this.dispatchEvent(slideChangeEvent);
            }
        });
    }

    /**
     * @protected
     */
    _onValueChange(value) {
        if (this._slider === undefined) {
            return;
        }

        this._slider.slider("value", value);
    }

    /**
     * @protected
     */
    _onMinChange(value) {
        if (this._slider === undefined) {
            return;
        }

        this._slider.slider("option", "min", value);
    }

    /**
     * @protected
     */
    _onMaxChange(value) {
        if (this._slider === undefined) {
            return;
        }

        this._slider.slider("option", "max", value);
    }
}

customElements.define(Slider.is, Slider);

export {Slider};