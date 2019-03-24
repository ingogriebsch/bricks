/*-
 * #%L
 * Bricks Model
 * %%
 * Copyright (C) 2018 - 2019 Ingo Griebsch
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.github.ingogriebsch.bricks.visualize.svg;

import static com.github.ingogriebsch.bricks.visualize.utils.svg.PresentationAttributes.FontVariant.SmallCaps;
import static com.github.ingogriebsch.bricks.visualize.utils.svg.PresentationAttributes.Visibility.Visible;

import java.awt.Point;
import java.io.OutputStream;

import com.github.ingogriebsch.bricks.model.Component;
import com.github.ingogriebsch.bricks.visualize.utils.svg.GroupBuilder;
import com.github.ingogriebsch.bricks.visualize.utils.svg.PolygonBuilder;
import com.github.ingogriebsch.bricks.visualize.utils.svg.SvgBuilder;
import com.github.ingogriebsch.bricks.visualize.utils.svg.SvgFactory;
import com.github.ingogriebsch.bricks.visualize.utils.svg.TextBuilder;

import org.w3c.dom.Element;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Component2SvgVisualizer {

    public void visualize(@NonNull Component component, @NonNull OutputStream output) throws Exception {
        SvgFactory svgFactory = SvgFactory.instance();

        Element outer = svgFactory.polygon((PolygonBuilder pb) -> {
            return pb.fill("green").fillOpacity("0.6").stroke("black").strokeWidth("1px").points(new Point(85, 17),
                new Point(35, 17), new Point(10, 60), new Point(35, 103), new Point(85, 103), new Point(110, 60)).build();
        });

        Element inner = svgFactory.polygon((PolygonBuilder pb) -> {
            return pb.fill("white").stroke("black").strokeWidth("1px").strokeDasharray("3,3").points(new Point(80, 25),
                new Point(40, 25), new Point(20, 60), new Point(40, 95), new Point(80, 95), new Point(100, 60)).build();
        });

        Element subject = svgFactory.text((TextBuilder tb) -> {
            return tb.x(10).y(120).fontFamily("arial").fontSize("80%").fontVariant(SmallCaps).content(component.getName())
                .build();
        });

        Element group = svgFactory.group((GroupBuilder gb) -> {
            return gb.visibility(Visible).element(outer).element(inner).element(subject).build();
        });

        svgFactory.svg((SvgBuilder sb) -> {
            return sb.element(group).build();
        });

        svgFactory.create(output);
    }
}
